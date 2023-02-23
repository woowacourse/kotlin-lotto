package lotto.controller

import lotto.constant.PURCHASE_UNIT
import lotto.domain.LottoBunch
import lotto.domain.LottoCount
import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.domain.LottoStore
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.utils.illegalArgumentExceptionHandler
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val lottoBunch = getLottoBunch(getLottoCount(purchaseMoney))
        val winningLotto = getWinningLotto()
        checkWinningResult(lottoBunch, winningLotto, purchaseMoney)
    }

    private fun getPurchaseMoney(): PurchaseMoney {
        return runCatching {
            PurchaseMoney(InputView.getPurchaseMoney())
        }.getOrElse { error ->
            illegalArgumentExceptionHandler(error, ::getPurchaseMoney)
        }
    }

    private fun getLottoCount(purchaseMoney: PurchaseMoney): LottoCount {
        return runCatching {
            LottoCount.from(purchaseMoney.getPurchaseCount(PURCHASE_UNIT), InputView.getManualLottoCount())
        }.getOrElse { error ->
            println(error.message)
            getLottoCount(purchaseMoney)
        }
    }

    private fun getLottoBunch(lottoCount: LottoCount): LottoBunch {
        val manualNumberLines = getManualNumberLines(lottoCount)
        return runCatching {
            LottoStore(LottoFactory()).buyLottoes(lottoCount, manualNumberLines)
        }.onSuccess {
            OutputView.printPurchaseCount(lottoCount.manualCount, lottoCount.autoCount)
            OutputView.printLottoBunch(it)
        }.getOrElse { error ->
            println(error.message)
            getLottoBunch(lottoCount)
        }
    }

    private fun getManualNumberLines(lottoCount: LottoCount): List<List<Int>> =
        InputView.getManualNumberLines(lottoCount.manualCount)

    private fun getMainLottoNumber(): Set<LottoNumber> {
        return runCatching {
            InputView.getMainLottoNumbers().map { number -> LottoNumber.from(number) }.toSet()
        }.getOrElse { error ->
            illegalArgumentExceptionHandler(error, ::getMainLottoNumber)
        }
    }

    private fun getBonusLottoNumber(): LottoNumber {
        return runCatching {
            LottoNumber.from(InputView.getBonusLottoNumber())
        }.getOrElse { error ->
            illegalArgumentExceptionHandler(error, ::getBonusLottoNumber)
        }
    }

    private fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            illegalArgumentExceptionHandler(error, ::getWinningLotto)
        }
    }

    private fun checkWinningResult(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        val ranks = lottoBunch.value.map { winningLotto.getRank(it) }
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult)
        OutputView.printYieldRate(winningResult.getYieldRate(purchaseMoney))
    }
}

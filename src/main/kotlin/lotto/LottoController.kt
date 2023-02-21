package lotto

import lotto.domain.Bank
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.factory.LottoFactory
import lotto.utils.inputErrorHandler
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val lottoBunch = getLottoBunch(purchaseMoney.getPurchaseCount())
        val winningLotto = getWinningLotto()
        confirmLottoWinning(lottoBunch, winningLotto, purchaseMoney)
    }

    private fun getLottoBunch(purchaseCount: Int): LottoBunch {
        val lottoBunch = LottoBunch(lottoFactory, purchaseCount)
        OutputView.printPurchaseResult(lottoBunch, purchaseCount)
        return lottoBunch
    }

    private fun getPurchaseMoney(): PurchaseMoney {
        return runCatching {
            PurchaseMoney(InputView.getPurchaseMoney())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getPurchaseMoney)
        }
    }

    private fun getMainLottoNumber(): List<LottoNumber> {
        return runCatching {
            InputView.getMainLottoNumber().map { number -> LottoNumber.from(number) }
        }.getOrElse { error ->
            inputErrorHandler(error, ::getMainLottoNumber)
        }
    }

    private fun getBonusLottoNumber(): LottoNumber {
        return runCatching {
            LottoNumber.from(InputView.getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getBonusLottoNumber)
        }
    }

    private fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getWinningLotto)
        }
    }

    private fun confirmLottoWinning(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        val ranks = lottoBunch.value.map { Bank.getRank(it, winningLotto) }
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult)
        OutputView.printYieldRate(Bank.getYieldRate(purchaseMoney, Bank.sumTotalPrizeMoney(lottoBunch, winningLotto)))
    }
}

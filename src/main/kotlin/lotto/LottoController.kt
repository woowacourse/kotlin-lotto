package lotto

import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.YieldRateCalculator
import lotto.domain.factory.LottoFactory
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val lottoBunch = getLottoBunch(purchaseMoney.getPurchaseCount())
        val winningLotto = getWinningLotto()
        confirmLottoWinning(purchaseMoney = purchaseMoney, lottoBunch = lottoBunch, winningLotto = winningLotto)
    }

    private fun getPurchaseMoney(): PurchaseMoney {
        return runCatching {
            PurchaseMoney(InputView.getPurchaseMoney())
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getPurchaseMoney()
        }
    }

    private fun getLottoBunch(purchaseCount: Int): LottoBunch {
        val lottoes = mutableListOf<Lotto>()
        repeat(purchaseCount) {
            lottoes.add(lottoFactory.createLotto())
        }

        val lottoBunch = LottoBunch(lottoes)
        OutputView.printPurchaseResult(lottoBunch, purchaseCount)
        return lottoBunch
    }

    private fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            println(error.message)
            getWinningLotto()
        }
    }

    private fun getMainLottoNumber(): Lotto {
        return kotlin.runCatching {
            Lotto(InputView.getMainLottoNumbers().map { LottoNumber(it) }.toSet())
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getMainLottoNumber()
        }
    }

    private fun getBonusLottoNumber(): LottoNumber {
        return runCatching {
            LottoNumber(InputView.getBonusLottoNumber())
        }.getOrElse {
            println(ERROR_NOT_NUMBER)
            getBonusLottoNumber()
        }
    }

    private fun confirmLottoWinning(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        OutputView.printWinningStatsScript()
        val ranks = lottoBunch.calcRanks(winningLotto)
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult)

        OutputView.printYieldRate(
            YieldRateCalculator.getYieldRate(
                purchaseMoney,
                winningResult.sumTotalPrizeMoney(),
            ),
        )
    }

    companion object {
        private const val ERROR_NOT_NUMBER = "숫자 이외의 입력이 들어갔거나 올바르지 않은 입력입니다."
    }
}

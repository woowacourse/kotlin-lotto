package lotto

import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.YieldRateCalculator
import lotto.domain.purchasecount.TotalPurchaseCount
import lotto.view.InputView
import lotto.view.OutputView

class LottoController() {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val totalPurchaseCount = getTotalPurchaseCount(purchaseMoney.getPurchaseCount())
        val lottoBunch = LottoBunch.from(totalPurchaseCount)
        val winningLotto = getWinningLotto()
        confirmLottoWinning(purchaseMoney = purchaseMoney, lottoBunch = lottoBunch, winningLotto = winningLotto)
    }

    private fun getPurchaseMoney(): PurchaseMoney = PurchaseMoney(InputView.getPurchaseMoney())

    private fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            println(error.message)
            getWinningLotto()
        }
    }

    private fun getMainLottoNumber(): Lotto = Lotto(InputView.getMainLottoNumbers().map { LottoNumber(it) }.toSet())

    private fun getBonusLottoNumber(): LottoNumber =
        LottoNumber(InputView.getBonusLottoNumber())

    private fun confirmLottoWinning(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        OutputView.printWinningStatsScript()
        val ranks = lottoBunch.calculateRanks(winningLotto)
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult)

        OutputView.printYieldRate(
            YieldRateCalculator.getYieldRate(
                purchaseMoney,
                winningResult.sumTotalPrizeMoney(),
            ),
        )
    }

    private fun getTotalPurchaseCount(totalPurchaseCount: Int): TotalPurchaseCount {
        return runCatching {
            TotalPurchaseCount(totalPurchaseCount, InputView.getManualPurchaseCount())
        }.getOrElse { error ->
            println(error.message)
            getTotalPurchaseCount(totalPurchaseCount)
        }
    }
}

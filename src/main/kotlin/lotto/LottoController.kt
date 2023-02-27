package lotto

import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.YieldRateCalculator
import lotto.domain.factory.LottoFactory
import lotto.domain.purchasecount.TotalPurchaseCount
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val totalPurchaseCount = getTotalPurchaseCount(purchaseMoney.getPurchaseCount())
        val manualPurchaseLotto = getManualPurchaseLotto(totalPurchaseCount)
        val lottoBunch = getLottoBunch(totalPurchaseCount, manualPurchaseLotto)
        val winningLotto = getWinningLotto()
        confirmLottoWinning(purchaseMoney = purchaseMoney, lottoBunch = lottoBunch, winningLotto = winningLotto)
    }

    private fun getPurchaseMoney(): PurchaseMoney = PurchaseMoney(InputView.getPurchaseMoney())

    private fun getLottoBunch(
        totalPurchaseCount: TotalPurchaseCount,
        manualPurchaseLotto: List<Lotto>,
    ): LottoBunch {
        val lottoBunch =
            LottoBunch(manualPurchaseLotto + List(totalPurchaseCount.autoPurchaseCount.value) { lottoFactory.createLotto() })
        OutputView.printPurchaseResult(
            totalPurchaseCount.manualPurchaseCount.value,
            totalPurchaseCount.autoPurchaseCount.value,
            lottoBunch,
        )
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

    private fun getManualPurchaseLotto(totalPurchaseCount: TotalPurchaseCount): List<Lotto> {
        return runCatching {
            InputView.getManualPurchaseLotto(totalPurchaseCount.manualPurchaseCount).map { convertToLotto(it) }
        }.getOrElse { error ->
            println(error.message)
            getManualPurchaseLotto(totalPurchaseCount)
        }
    }

    private fun getTotalPurchaseCount(totalPurchaseCount: Int): TotalPurchaseCount {
        return runCatching {
            TotalPurchaseCount(totalPurchaseCount, InputView.getManualPurchaseCount())
        }.getOrElse { error ->
            println(error.message)
            getTotalPurchaseCount(totalPurchaseCount)
        }
    }

    private fun convertToLotto(numbers: List<Int>): Lotto =
        Lotto(numbers.map { LottoNumber(it) }.toSet())
}

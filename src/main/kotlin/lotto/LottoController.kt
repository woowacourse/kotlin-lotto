package lotto

import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.ManualPurchaseCount
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.YieldRateCalculator
import lotto.domain.factory.LottoFactory
import lotto.view.InputView
import lotto.view.InputView.getManualPurchaseCount
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val purchaseCount = purchaseMoney.getPurchaseCount()
        val manualPurchaseCount = getManualPurchaseCount(purchaseCount)
        val manualPurchaseLotto = getManualPurchaseLotto(manualPurchaseCount)
        val lottoBunch = getLottoBunch(purchaseCount, manualPurchaseCount, manualPurchaseLotto)
        val winningLotto = getWinningLotto()
        confirmLottoWinning(purchaseMoney = purchaseMoney, lottoBunch = lottoBunch, winningLotto = winningLotto)
    }

    private fun getPurchaseMoney(): PurchaseMoney = PurchaseMoney(InputView.getPurchaseMoney())

    private fun getLottoBunch(
        purchaseCount: Int,
        manualPurchaseCount: ManualPurchaseCount,
        manualPurchaseLotto: List<Lotto>,
    ): LottoBunch {
        val autoPurchaseCount = getAutoPurchaseCount(purchaseCount, manualPurchaseCount)
        val lottoBunch = LottoBunch(manualPurchaseLotto + List(autoPurchaseCount) { lottoFactory.createLotto() })

        OutputView.printPurchaseResult(manualPurchaseCount.value, autoPurchaseCount, lottoBunch)
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

    private fun getManualPurchaseCount(purchaseCount: Int): ManualPurchaseCount {
        return runCatching {
            ManualPurchaseCount.from(getManualPurchaseCount(), purchaseCount)
        }.getOrElse { error ->
            println(error.message)
            getManualPurchaseCount(purchaseCount)
        }
    }

    private fun getManualPurchaseLotto(manualPurchaseCount: ManualPurchaseCount): List<Lotto> {
        return runCatching {
            InputView.getManualPurchaseLotto(manualPurchaseCount).map { convertToLotto(it) }
        }.getOrElse { error ->
            println(error.message)
            getManualPurchaseLotto(manualPurchaseCount)
        }
    }

    private fun convertToLotto(numbers: List<Int>): Lotto =
        Lotto(numbers.map { LottoNumber(it) }.toSet())

    private fun getAutoPurchaseCount(purchaseCount: Int, manualPurchaseCount: ManualPurchaseCount): Int =
        purchaseCount - manualPurchaseCount.value
}

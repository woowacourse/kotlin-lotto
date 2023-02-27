package lotto.domain

import lotto.domain.factory.LottoFactory
import lotto.domain.factory.ManualLottoFactory
import lotto.domain.factory.RandomLottoFactory
import lotto.domain.purchasecount.TotalPurchaseCount
import lotto.view.InputView
import lotto.view.OutputView

class LottoBunch(val value: List<Lotto>) {

    fun calculateRanks(winningLotto: WinningLotto): List<Rank> =
        value.map { winningLotto.calculateRank(it) }

    companion object {
        private val autoLottoFactory: LottoFactory = RandomLottoFactory()
        private val manualLottoFactory: LottoFactory = ManualLottoFactory()
        fun from(totalPurchaseCount: TotalPurchaseCount): LottoBunch =
            getLottoBunch(totalPurchaseCount)

        private fun getLottoBunch(
            totalPurchaseCount: TotalPurchaseCount,
        ): LottoBunch {
            val lottoBunch =
                LottoBunch(getManualPurchaseLotto(totalPurchaseCount) + getAutoPurchaseLotto(totalPurchaseCount))
            OutputView.printPurchaseResult(
                totalPurchaseCount.manualPurchaseCount.value,
                totalPurchaseCount.autoPurchaseCount.value,
                lottoBunch,
            )
            return lottoBunch
        }

        private fun getAutoPurchaseLotto(totalPurchaseCount: TotalPurchaseCount): List<Lotto> =
            List(totalPurchaseCount.autoPurchaseCount.value) { autoLottoFactory.createLotto() }

        private fun getManualPurchaseLotto(totalPurchaseCount: TotalPurchaseCount): List<Lotto> {
            InputView.printManualPurchaseLottoScript()
            return List(totalPurchaseCount.manualPurchaseCount.value) { manualLottoFactory.createLotto() }
        }
    }
}

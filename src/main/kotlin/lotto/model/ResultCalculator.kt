package lotto.model

import lotto.constants.LottoPrize
import kotlin.math.floor

object ResultCalculator {

    fun calculatePrizeCount(lottoStore: LottoStore, winningLotto: WinningLotto) =
        lottoStore.lottos
            .map { lotto -> lotto.compare(winningLotto.lotto, winningLotto.bonusNumber) }
            .groupBy { it }
            .mapValues { it.value.size }

    fun calculateProfitRatio(purchaseInfo: PurchaseInfo, prizeCount: Map<LottoPrize, Int>): Double {
        val totalPrizeAmount = prizeCount
            .map { (lottoPrize, count) ->
                lottoPrize.amount * count.toLong()
            }.sum()

        return (totalPrizeAmount.toDouble() / purchaseInfo.price).roundTwoDecimal()
    }

    private fun Double.roundTwoDecimal() = floor(this * 100) / 100

}

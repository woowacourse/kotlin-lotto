package lotto.service

import lotto.model.LottoPrize
import lotto.model.PurchaseInfo
import kotlin.math.floor

class WinningStatics {
    fun calculateProfitRatio(
        purchaseInfo: PurchaseInfo,
        prizeCount: Map<LottoPrize, Int>,
    ): Double {
        val totalPrizeAmount =
            prizeCount
                .map { (lottoPrize, count) ->
                    lottoPrize.amount * count.toLong()
                }.sum()

        return (totalPrizeAmount.toDouble() / purchaseInfo.price).roundTwoDecimal()
    }

    private fun Double.roundTwoDecimal() = floor(this * 100) / 100
}

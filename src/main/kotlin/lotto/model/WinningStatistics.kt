package lotto.model

import lotto.constants.LottoPrize

class WinningStatistics(private val statistics: Map<LottoPrize, Int>) {
    fun calculateProfitRatio(purchaseInfo: PurchaseInfo): ProfitRatio {
        val totalPrizeAmount =
            statistics
                .map { (lottoPrize, count) -> lottoPrize.amount * count.toLong() }
                .sum()

        return ProfitRatio(totalPrizeAmount.toDouble() / purchaseInfo.price)
    }

    operator fun get(lottoPrize: LottoPrize) = statistics.getOrDefault(lottoPrize, DEFAULT_LOTTO_PRIZE_COUNT)

    companion object {
        private const val DEFAULT_LOTTO_PRIZE_COUNT = 0
    }
}

package lotto.model

class WinningStatistics(private val statistics: Map<LottoPrize, Int>) {
    fun calculateProfitRatio(purchaseOrder: PurchaseOrder): ProfitRatio {
        val totalPrizeAmount =
            statistics
                .map { (lottoPrize, count) -> lottoPrize.amount * count.toLong() }
                .sum()
        return ProfitRatio(totalPrizeAmount.toDouble() / purchaseOrder.price)
    }

    operator fun get(lottoPrize: LottoPrize) = statistics.getOrDefault(lottoPrize, DEFAULT_LOTTO_PRIZE_COUNT)

    companion object {
        private const val DEFAULT_LOTTO_PRIZE_COUNT = 0
    }
}

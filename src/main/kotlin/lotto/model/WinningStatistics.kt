package lotto.model

class WinningStatistics(private val statistics: Map<LottoPrize, Int>) {
    fun calculateProfitRatio(purchasePrice: Int): ProfitRatio {
        val totalPrizeAmount = calculateTotalPrizeAmount()
        return ProfitRatio(totalPrizeAmount / purchasePrice.toDouble())
    }

    private fun calculateTotalPrizeAmount(): Long {
        return statistics
            .map { (lottoPrize, count) -> lottoPrize.amount * count.toLong() }
            .sum()
    }

    operator fun get(lottoPrize: LottoPrize): Int {
        return statistics.getOrDefault(lottoPrize, DEFAULT_LOTTO_PRIZE_COUNT)
    }

    companion object {
        private const val DEFAULT_LOTTO_PRIZE_COUNT = 0
    }
}

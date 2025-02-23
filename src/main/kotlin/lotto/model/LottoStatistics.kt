package lotto.model

class LottoStatistics(
    val rankStatistics: Map<Rank, Int>,
    private val purchaseMoney: LottoPurchaseAmount,
) {
    val rateOfReturn: Double = calculateRateOfReturn()

    private fun calculateRateOfReturn(): Double {
        val totalPrize = getTotalPrize()
        val rateOfReturn = totalPrize / purchaseMoney.money

        return rateOfReturn
    }

    private fun getTotalPrize(): Double {
        var sum = INITIAL_TOTAL_PRIZE
        rankStatistics.forEach { (rank, count) ->
            sum += rank.prizeMoney * count
        }

        return sum
    }

    fun isLossMoney(): Boolean = rateOfReturn < 1

    companion object {
        private const val INITIAL_TOTAL_PRIZE = 0.0
    }
}

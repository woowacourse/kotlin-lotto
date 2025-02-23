package lotto.model

class LottoStatistics(
    val rankStatistics: Map<Rank, Int>,
    private val purchaseMoney: LottoPurchaseAmount,
) {
    fun getRateOfReturn(): Double {
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

    fun getIsLossMoney(rateOfReturn: Double): Boolean = rateOfReturn < 1

    companion object {
        private const val INITIAL_TOTAL_PRIZE = 0.0
    }
}

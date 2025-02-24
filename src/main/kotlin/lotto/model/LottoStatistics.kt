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

    private fun getTotalPrize(): Double = rankStatistics.asIterable().sumOf { (rank, count) -> rank.prizeMoney * count }.toDouble()

    fun isLossMoney(): Boolean = rateOfReturn < 1
}

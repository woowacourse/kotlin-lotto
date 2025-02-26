package lotto.model

import kotlin.math.floor
import kotlin.math.pow

class LottoStatistics(
    val rankStatistics: Map<Rank, Int>,
    private val purchaseMoney: LottoPurchaseAmount,
) {
    val rateOfReturn: Double = calculateRateOfReturn()

    private fun calculateRateOfReturn(): Double {
        val totalPrize = getTotalPrize()
        val rateOfReturn = totalPrize / purchaseMoney.money
        return rateOfReturn.floorToDecimalPlaces(DECIMAL_PLACES)
    }

    fun isLossMoney(): Boolean = rateOfReturn < 1

    private fun getTotalPrize(): Double = rankStatistics.asIterable().sumOf { (rank, count) -> rank.prizeMoney * count }.toDouble()

    private fun Double.floorToDecimalPlaces(decimalPlaces: Int): Double {
        val scaleFactor = 10.0.pow((decimalPlaces - 1).toDouble())
        return floor(this * scaleFactor) / scaleFactor
    }

    companion object {
        private const val DECIMAL_PLACES = 3
    }
}

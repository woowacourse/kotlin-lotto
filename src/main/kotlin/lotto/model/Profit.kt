package lotto.model

import kotlin.math.floor

class Profit(
    private val profit: Double,
) {
    override fun toString(): String = profit.toString()

    companion object {
        private const val PERCENT_FACTOR = 100

        fun calculateProfit(
            input: String,
            winningStatistics: WinningStatistics,
        ): Profit {
            val purchaseAmount = input.toDouble()
            val sum =
                winningStatistics.getAllStatistics().entries.sumOf { (rank, winningCount) ->
                    winningCount * rank.winningMoney
                }
            return Profit(floor(sum.toDouble() / purchaseAmount * PERCENT_FACTOR) / PERCENT_FACTOR)
        }
    }
}

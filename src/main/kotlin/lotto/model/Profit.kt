package lotto.model

import kotlin.math.floor

class Profit {
    companion object {
        fun calculateProfit(
            purchase: Double,
            winningStatistics: WinningStatistics,
        ): String {
            val sum =
                winningStatistics.getAllStatistics().entries.sumOf { (rank, winningCount) ->
                    winningCount * rank.winningMoney
                }
            val profit = floor((sum.toDouble() / purchase) * PERCENT_FACTOR) / PERCENT_FACTOR
            return profit.toString()
        }

        private const val PERCENT_FACTOR = 100
    }
}

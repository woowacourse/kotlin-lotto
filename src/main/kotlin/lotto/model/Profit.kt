package lotto.model

import kotlin.math.floor

class Profit {
    fun calculateProfit(
        input: String,
        winningStatistics: WinningStatistics,
    ): String {
        val purchaseAmount = input.toDouble()
        val sum =
            winningStatistics.getAllStatistics().entries.sumOf { (rank, winningCount) ->
                winningCount * rank.winningMoney
            }
        val profit = floor((sum.toDouble() / purchaseAmount) * PERCENT_FACTOR) / PERCENT_FACTOR
        return profit.toString()
    }

    companion object {
        private const val PERCENT_FACTOR = 100
    }
}

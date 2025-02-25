package lotto.model

import kotlin.math.floor

class LottoStatisticResult {
    private fun getTotalWinningPrice(winningCounts: Map<Rank, Int>): Int =
        winningCounts.entries.sumOf { (rank, winningCount) -> winningCount * rank.winningMoney }

    fun calculateProfit(
        purchasePrice: Int,
        winningCounts: Map<Rank, Int>,
    ): String {
        val totalWinningPrice = getTotalWinningPrice(winningCounts)
        return (floor((totalWinningPrice.toDouble() / purchasePrice) * PERCENT_FACTOR) / PERCENT_FACTOR).toString()
    }

    companion object {
        private const val PERCENT_FACTOR = 100
    }
}

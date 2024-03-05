package lotto.model

data class WinningStatistics(val results: Map<Rank, Int>) {
    fun calculateRateOfReturn(purchaseAmount: Int): Double {
        val totalWinningAmount = calculateTotalWinningAmount()
        return totalWinningAmount.toDouble() / purchaseAmount
    }

    private fun calculateTotalWinningAmount(): Int {
        return results.entries.sumOf { (rank, count) ->
            rank.winningAmount * count
        }
    }
}

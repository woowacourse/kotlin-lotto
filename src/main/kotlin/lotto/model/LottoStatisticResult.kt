package lotto.model

class LottoStatisticResult {
    private fun getTotalWinningPrice(winningCounts: Map<Rank, Int>): Int =
        winningCounts.entries.sumOf { (rank, winningCount) -> winningCount * rank.winningMoney }

    fun calculateProfit(
        purchasePrice: Int,
        winningCounts: Map<Rank, Int>,
    ): String {
        val totalWinningPrice = getTotalWinningPrice(winningCounts)
        val profitRate = totalWinningPrice.toDouble() / purchasePrice.toDouble()
        return String.format("%.2f", profitRate)
//      return (floor((totalWinningPrice.toDouble() / purchasePrice) * PERCENT_FACTOR) / PERCENT_FACTOR).toString()
    }

    companion object {
        private const val PERCENT_FACTOR = 100
    }
}

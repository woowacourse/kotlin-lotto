package model

class WinningStatistics(private val results: List<WinningStatistic>) {
    fun calculateRateOfReturn(purchaseAmount: Int): Double {
        val totalWinningAmount = calculateTotalWinningAmount()
        return totalWinningAmount.toDouble() / purchaseAmount
    }

    private fun calculateTotalWinningAmount(): Int {
        var totalWinningAmount = 0
        results.forEach { winningStatistic ->
            val winningMoney = winningStatistic.getResult().first.getWinningAmount()
            val count = winningStatistic.getResult().second
            totalWinningAmount += winningMoney * count
        }

        return totalWinningAmount
    }

    override fun toString(): String {
        return results.subList(0, results.size - 1).reversed().joinToString("\n")
    }
}

package model

class WinningStatistics(private val winningStatistics: List<WinningStatistic>) {
    fun calculateRateOfReturn(purchaseAmount: Int): Double {
        val totalWinningAmount = calculateTotalWinningAmount()
        return totalWinningAmount.toDouble() / purchaseAmount
    }

    private fun calculateTotalWinningAmount(): Int {
        var totalWinningAmount = 0
        winningStatistics.forEach { winningStatistic ->
            val winningMoney = winningStatistic.getWinningStatistic().first.getWinningAmount()
            val count = winningStatistic.getWinningStatistic().second
            totalWinningAmount += winningMoney * count
        }

        return totalWinningAmount
    }

    override fun toString(): String {
        return winningStatistics.subList(0, winningStatistics.size - 1).reversed().joinToString("\n")
    }
}

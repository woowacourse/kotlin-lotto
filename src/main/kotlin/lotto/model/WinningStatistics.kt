package lotto.model

import lotto.util.Constant

class WinningStatistics(private val winningStatistics: List<WinningStatistic>) {
    fun calculateRateOfReturn(numberOfLotto: Int): Double {
        val purchaseAmount = numberOfLotto * Constant.PURCHASE_AMOUNT_UNIT
        val totalWinningAmount = calculateTotalWinningAmount()
        return totalWinningAmount.toDouble() / purchaseAmount
    }

    fun getWinningStatistics(): List<WinningStatistic> = winningStatistics

    private fun calculateTotalWinningAmount(): Int {
        var totalWinningAmount = 0
        winningStatistics.forEach { winningStatistic ->
            val winningMoney = winningStatistic.getWinningStatistic().first.getWinningAmount()
            val count = winningStatistic.getWinningStatistic().second
            totalWinningAmount += winningMoney * count
        }
        return totalWinningAmount
    }
}

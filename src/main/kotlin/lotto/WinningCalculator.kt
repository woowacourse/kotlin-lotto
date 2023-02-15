package lotto

import java.text.DecimalFormat

class WinningCalculator {
    fun getWinningMoney(statistics: List<Int>): Int {
        var money = 0

        Rank.values().forEach { rank ->
            money += rank.winningMoney * statistics[rank.ordinal]
        }

        return money
    }

    fun getEarningRate(purchase: Int, winning: Int): Double {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(winning.toDouble() / purchase).toDouble()
    }
}

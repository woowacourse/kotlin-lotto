package lotto.domain

import lotto.model.Rank
import java.text.DecimalFormat

object WinningCalculator {
    fun getWinningMoney(statistics: List<Int>): Int {
        var money = 0

        Rank.values().forEach { rank ->
            money += rank.winningMoney * statistics[rank.ordinal]
        }

        return money
    }

    fun getEarningRate(purchase: Int, winning: Int): String {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(winning.toDouble() / purchase)
    }
}

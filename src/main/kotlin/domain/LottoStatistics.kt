package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto) {
    private fun getCountOfMatch(lotto: Lotto): Int = winningLotto.match(lotto)

    private fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.contains(winningLotto.bonusNumber)

    private fun getRank(lotto: Lotto): Rank = Rank.valueOf(getCountOfMatch(lotto), isBonusNumberMatch(lotto))

    fun compareTicket(ticket: Ticket): Map<Rank, Int> {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.forEach { lotto ->
            result[getRank(lotto)] = result[getRank(lotto)]!! + 1
        }
        return result
    }

    fun calculateProfit(results: Map<Rank, Int>): Int {
        var sum = 0
        for (result in results) {
            val rank = result.key
            sum += rank.winningMoney * result.value
        }
        return sum
    }

    fun calculateProfitRatio(profit: Int, totalMoney: Money): String {
        return floor((profit / totalMoney.money).toDouble()).toString()
    }
}

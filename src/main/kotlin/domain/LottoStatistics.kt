package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto, private val ticket: Ticket) {
    private fun getCountOfMatch(lotto: Lotto): Int = winningLotto.match(lotto)

    private fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.contains(winningLotto.bonusNumber)

    private fun getRank(lotto: Lotto): Rank = Rank.valueOf(getCountOfMatch(lotto), isBonusNumberMatch(lotto))

    private fun calculateProfit(): Int {
        val results = getWinningCountBy()
        var sum = 0
        for (result in results) {
            val rank = result.key
            sum += rank.winningMoney * result.value
        }
        return sum
    }

    fun getWinningCountBy(): Map<Rank, Int> {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.lottos.forEach { lotto ->
            result[getRank(lotto)] = result[getRank(lotto)]!! + 1
        }
        return result
    }

    fun calculateProfitRatio(totalMoney: Money): String {
        return floor((calculateProfit() / totalMoney.money).toDouble()).toString()
    }
}

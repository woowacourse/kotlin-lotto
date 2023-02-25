package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto, private val ticket: Ticket) {
    private fun getCountOfMatch(lotto: Lotto): Int = winningLotto.match(lotto)

    private fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.contains(winningLotto.bonusNumber)

    private fun getRank(lotto: Lotto): Rank = Rank.valueOf(getCountOfMatch(lotto), isBonusNumberMatch(lotto))

    private fun getProfit(): Int {
        val results = getWinningCountBy()
        var sum = 0
        results.forEach { winningCountBy ->
            sum += winningCountBy.key.winningMoney * winningCountBy.value
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

    fun getProfitRatio(totalMoney: Money): String {
        return floor((getProfit() / totalMoney.money).toDouble()).toString()
    }
}

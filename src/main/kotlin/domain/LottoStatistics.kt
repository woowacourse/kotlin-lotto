package domain

import kotlin.math.floor

class LottoStatistics(val winningLotto: WinningLotto) {

    private fun getCountOfMatch(lotto: Lotto): Int {
        return winningLotto.match(lotto)
    }

    private fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.numbers.contains(winningLotto.bonusNumber)

    private fun getRank(lotto: Lotto): Rank = Rank.valueOf(getCountOfMatch(lotto), isBonusNumberMatch(lotto))

    fun compareTicket(ticket: Ticket): Map<Rank, Int> {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.lottos.forEach { lotto ->
            result[getRank(lotto)] = result[getRank(lotto)]!! + 1
        }

        return result
    }

    fun calculateProfit(results: Map<Rank, Int>): String {
        var sum = 0.0
        var totalCount = 0.0
        for (result in results) {
            sum += Rank.values()[result.key.ordinal].winningMoney * result.value
            totalCount += result.value
        }

        return (floor((sum / (totalCount * 1000)) * 100) / 100).toString()
    }
}

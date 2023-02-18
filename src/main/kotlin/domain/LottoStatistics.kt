package domain

import kotlin.math.floor

class LottoStatistics(val winningLotto: WinningLotto) {

    private fun getCountOfMatch(lotto: Lotto): Int {
        val winningNumbers = winningLotto.getWinningNumbers()
        return lotto.numbers.filter { number ->
            winningNumbers.contains(number)
        }.size
    }

    private fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.numbers.contains(winningLotto.bonusNumber)

    private fun getRank(lotto: Lotto): Rank = Rank.valueOf(getCountOfMatch(lotto), isBonusNumberMatch(lotto))

    fun compareTicket(ticket: Ticket): List<Int> {
        val result = MutableList(6) { 0 }
        ticket.lottos.forEach { lotto ->
            result[getRank(lotto).ordinal] += 1
        }
        return result
    }

    fun calculateProfitToString(result: List<Int>): String {
        var sum = 0.0
        var totalCount = 0.0
        for ((index, count) in result.withIndex()) {
            sum += Rank.values()[index].winningMoney * count
            totalCount += count
        }
        return (floor((sum / (totalCount * 1000)) * 100) / 100).toString()
    }
}

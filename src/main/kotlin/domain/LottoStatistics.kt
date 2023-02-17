package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto) {

    fun compareNumbers(lotto: Lotto): Int {
        val winningNumbers = winningLotto.getWinningNumbers()
        return lotto.numbers.filter { number ->
            winningNumbers.contains(number)
        }.size
    }

    fun compareBonusNumber(lotto: Lotto): Boolean = lotto.numbers.contains(winningLotto.bonusNumber)

    fun compare(lotto: Lotto): Rank = Rank.valueOf(compareNumbers(lotto), compareBonusNumber(lotto))

    fun compareTicket(ticket: Ticket): Map<Rank, Int> {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.lottos.forEach { lotto ->
            result[compare(lotto)] = (result[compare(lotto)] ?: 0) + 1
        }
        return result
    }

    fun yield(result: Map<Rank, Int>): String {
        var sum = 0.0
        var totalCount = 0.0
        for ((rank, count) in result) {
            sum += rank.winningMoney * count
            totalCount += count
        }
        return (floor((sum / (totalCount * 1000)) * 100) / 100).toString()
    }
}

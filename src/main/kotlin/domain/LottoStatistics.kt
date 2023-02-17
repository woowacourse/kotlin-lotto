package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto) {

    fun matchNumbers(lotto: Lotto): Int {
        val winningNumbers = winningLotto.getWinningNumbers()
        return lotto.numbers.count { winningNumbers.contains(it) }
    }

    fun matchBonusNumber(lotto: Lotto): Boolean = lotto.numbers.contains(winningLotto.bonusNumber)

    fun match(lotto: Lotto): Rank = Rank.valueOf(matchNumbers(lotto), matchBonusNumber(lotto))

    fun matchTicket(ticket: Ticket): Map<Rank, Int> {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.lottos.forEach { lotto ->
            result[match(lotto)] = (result[match(lotto)] ?: 0) + 1
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
        return (floor((sum / (totalCount * ONE_LOTTO_MONEY)) * 100) / 100).toString()
    }

    companion object {
        private const val ONE_LOTTO_MONEY = 1000
    }
}

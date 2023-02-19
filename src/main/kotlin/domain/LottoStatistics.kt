package domain

import domain.PurchaseLottoMoney.Companion.ONE_LOTTO_MONEY
import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto) {

    fun matchTicket(ticket: Ticket): Map<Rank, Int> {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.lottos.forEach { lotto ->
            val matchRank = lotto.matchResult(winningLotto)
            result[matchRank] = (result[matchRank] ?: 0) + 1
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
}

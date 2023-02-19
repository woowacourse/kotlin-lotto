package domain

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

    fun yield(result: Map<Rank, Int>, purchaseLottoMoney: PurchaseLottoMoney): String {
        var sum = 0.0
        for ((rank, count) in result) {
            sum += rank.winningMoney * count
        }
        return (floor((sum / (purchaseLottoMoney.money)) * 100) / 100).toString()
    }
}

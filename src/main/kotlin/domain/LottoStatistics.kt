package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto) {

    fun matchTicket(ticket: Ticket): LottoResult {
        val result = Rank.values().associateWith { 0 }.toMutableMap()
        ticket.lottos.forEach { lotto ->
            val matchRank = winningLotto.matchResult(lotto)
            result[matchRank] = (result[matchRank] ?: 0) + 1
        }
        return LottoResult(result)
    }

    fun yield(lottoResult: LottoResult, purchaseLottoMoney: PurchaseLottoMoney): String {
        val sum = lottoResult.sumWinningMoney()
        return (floor((sum / (purchaseLottoMoney.money)) * 100) / 100).toString()
    }
}

package domain

import kotlin.math.floor

class LottoStatistics(private val winningLotto: WinningLotto) {

    fun matchTicket(ticket: Ticket): LottoResult {
        val result = ticket.matchTicketCount(winningLotto) as MutableMap
        Rank.values().filter { result[it] == null }.forEach {
            result[it] = 0
        }
        return LottoResult(result)
    }

    fun yield(lottoResult: LottoResult, purchaseLottoMoney: PurchaseLottoMoney): String {
        val sum = lottoResult.sumWinningMoney()
        return (floor((sum / (purchaseLottoMoney.money)) * 100) / 100).toString()
    }
}

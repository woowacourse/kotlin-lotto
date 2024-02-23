package model

import model.LottoNumber.Companion.MAX_LOTTO_NUMBER
import model.LottoNumber.Companion.MIN_LOTTO_NUMBER

class LottoPurchase(private val purchasePrice: Int) {
    fun makeLottoCount(): Int {
        return purchasePrice / PRICE_OF_LOTTO_TICKET
    }

    private fun makeUserTicket(): LottoTicket {
        return LottoTicket(
            (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).shuffled().take(6).sorted().map { LottoNumber(it) },
        )
    }

    fun makeUserTickets(): List<LottoTicket> {
        val userTickets: MutableList<LottoTicket> = mutableListOf()
        repeat(makeLottoCount()) {
            userTickets.add(makeUserTicket())
        }
        return userTickets
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}

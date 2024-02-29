package lotto.model

import lotto.model.LottoNumber.Companion.MAX_LOTTO_NUMBER
import lotto.model.LottoNumber.Companion.MIN_LOTTO_NUMBER

class AutoLottoMachine(
    private val purchasePrice: Int,
    private val manualLottoCount: Int,
) {
    private fun makeUserTicket(): UserLottoTicket {
        return UserLottoTicket(
            (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).shuffled().take(6).sorted()
                .map { LottoNumber.makeLottoNumber(it) },
        )
    }

    fun makeAutoTickets(): List<UserLottoTicket> {
        val autoLottoCount: Int = purchasePrice / PRICE_OF_LOTTO_TICKET - manualLottoCount
        val userTickets = List(autoLottoCount) { makeUserTicket() }
        return userTickets
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}

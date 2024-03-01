package lotto.model

class LottoMachine {
    fun make(numbers: List<List<Int>>): List<UserLottoTicket> {
        val lottoTickets =
            numbers.map { UserLottoTicket(it.sorted().map { LottoNumber.makeLottoNumber(it) }) }
        return lottoTickets
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}

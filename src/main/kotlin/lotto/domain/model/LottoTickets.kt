package lotto.domain.model

class LottoTickets(val tickets: List<Lotto>) {
    val size: Int
        get() = tickets.size

    fun concat(tailLottoTickets: LottoTickets): LottoTickets = LottoTickets(tickets + tailLottoTickets.tickets)
}

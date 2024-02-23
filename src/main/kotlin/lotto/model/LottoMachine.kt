package lotto.model

import lotto.model.LottoNumber.Companion.NUMBER_RANGE

object LottoMachine {
    const val TICKET_PRICE = 1_000

    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE

    fun issueTickets(count: Int): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(count) {
            tickets.add(
                Lotto(NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted().map { LottoNumber(it) }),
            )
        }
        return tickets
    }
}

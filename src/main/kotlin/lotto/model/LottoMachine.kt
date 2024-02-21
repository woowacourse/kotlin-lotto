package lotto.model

object LottoMachine {
    const val TICKET_PRICE = 1_000

    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE

    fun issueTickets(count: Int): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(count) {
            tickets.add(Lotto(Lotto.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted()))
        }
        return tickets
    }
}

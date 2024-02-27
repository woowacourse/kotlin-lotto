package lotto.model

import lotto.util.NumberGenerate

class LottoMachine(ticketCounts: TicketCounts) {
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun issueTickets(
        count: Int,
        numberGenerate: NumberGenerate = randomNumberGenerate,
    ): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(count) { idx ->
            tickets.add(
                Lotto(numberGenerate.get(idx).map { LottoNumber(it) }),
            )
        }
        return tickets
    }
}

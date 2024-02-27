package lotto.model

import lotto.util.NumberGenerate

class LottoMachine(val ticketCounts: TicketCounts) {
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun issueManualTickets(manualLotto: List<List<Int>>): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(ticketCounts.numberOfManual.counts) { idx ->
            tickets.add(Lotto(manualLotto[idx].map { LottoNumber(it) }))
        }
        return tickets
    }

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

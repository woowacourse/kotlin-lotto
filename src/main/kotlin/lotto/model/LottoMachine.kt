package lotto.model

import lotto.util.NumberGenerate

class LottoMachine(val ticketCounts: TicketCounts) {
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun issueTickets(manualLotto: List<List<Int>> = listOf()): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        if (ticketCounts.numberOfManual.counts > NumberOfManual.MIN_NUMBER_OF_MANUAL) {
            tickets.addAll(issueManualTickets(manualLotto))
        }
        tickets.addAll(
            issueAutomaticTickets(ticketCounts.numberOfTickets.counts - ticketCounts.numberOfManual.counts),
        )
        return tickets
    }

    fun issueManualTickets(manualLotto: List<List<Int>>): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(ticketCounts.numberOfManual.counts) { idx ->
            tickets.add(Lotto(manualLotto[idx].map { LottoNumber(it) }))
        }
        return tickets
    }

    fun issueAutomaticTickets(
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

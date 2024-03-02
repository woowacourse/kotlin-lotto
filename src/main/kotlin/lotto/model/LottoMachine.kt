package lotto.model

import lotto.util.NumberGenerate

class LottoMachine(val ticketCounts: TicketCounts) {
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun issueTickets(manualLotto: List<String> = listOf()): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        if (ticketCounts.numberOfManual.counts > NumberOfManual.MIN_NUMBER_OF_MANUAL) {
            tickets.addAll(issueManualTickets(manualLotto))
        }
        tickets.addAll(issueAutomaticTickets(ticketCounts.getAutomaticTicketCounts()))
        return tickets
    }

    fun issueManualTickets(manualLotto: List<String>): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(manualLotto.size) { idx ->
            tickets.add(
                Lotto(manualLotto[idx].split(",").map { LottoNumber(it.trim()) }),
            )
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

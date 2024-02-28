package lotto.model

import lotto.util.NumberGenerate

class LottoMachine(val ticketCounts: TicketCounts) {
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun issueTickets(manualLotto: List<Lotto> = listOf()): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        if (ticketCounts.numberOfManual.counts > NumberOfManual.MIN_NUMBER_OF_MANUAL) {
            tickets.addAll(manualLotto)
        }
        tickets.addAll(
            issueAutomaticTickets(ticketCounts.getAutomaticTicketCounts()),
        )
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

package lotto.model

import lotto.util.NumberGenerate

object LottoMachine {
    const val TICKET_PRICE = 1_000
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE

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

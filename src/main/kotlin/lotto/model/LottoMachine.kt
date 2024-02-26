package lotto.model

import lotto.util.NumbersGenerator

object LottoMachine {
    const val TICKET_PRICE = 1_000

    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE

    fun issueTickets(
        count: Int,
        lottoNumbersGenerator: NumbersGenerator,
    ): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(count) {
            tickets.add(
                Lotto(lottoNumbersGenerator.generateNumbers().map { LottoNumber.valueOf(it) }.toSet()),
            )
        }
        return tickets
    }
}

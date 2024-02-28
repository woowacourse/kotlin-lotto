package lotto.model

import lotto.util.NumbersGenerator

object LottoMachine {
    const val TICKET_PRICE = 1_000

    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE

    fun issueAutoTickets(
        count: Int,
        lottoNumbersGenerator: NumbersGenerator,
    ): List<Lotto> =
        List(count) {
            Lotto(lottoNumbersGenerator.generateNumbers().map { LottoNumber.valueOf(it) }.toSet())
        }

    fun issueManualTickets(numbers: List<Set<Int>>): List<Lotto> =
        numbers.map { number -> Lotto(number.map { LottoNumber.valueOf(it) }.toSet()) }
}

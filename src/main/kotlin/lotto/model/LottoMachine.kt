package lotto.model

import lotto.util.NumbersGenerator

object LottoMachine {
    fun issueAutoTickets(
        count: Int,
        lottoNumbersGenerator: NumbersGenerator,
    ): List<Lotto> =
        List(count) {
            Lotto(lottoNumbersGenerator.generateNumbers().mapNotNull { LottoNumber.valueOf(it) }.toSet())
        }

    fun issueManualTickets(numbers: List<Set<Int>>): List<Lotto> =
        numbers.map { number -> Lotto(number.mapNotNull { LottoNumber.valueOf(it) }.toSet()) }
}

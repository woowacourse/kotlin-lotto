package lotto.model

object LottoMachine {
    fun issueTickets(numbers: List<Set<Int>>): List<Lotto> =
        numbers.map { number -> Lotto(number.mapNotNull { LottoNumber.valueOf(it) }.toSet()) }
}

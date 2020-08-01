package lotto.domain

class LottoTicket(numbers: List<LottoNumber>) {
    companion object {
        const val LOTTO_TICKET_SIZE = 6
    }

    val lottoNumbers: List<LottoNumber> = numbers.sorted()

    init {
        check(lottoNumbers.toSet().size == LOTTO_TICKET_SIZE)
    }

    fun countOfSameNumbers(winningNumbers: LottoTicket): Int {
        return winningNumbers.lottoNumbers
            .filter { lottoNumbers.contains(it) }
            .count()
    }
}

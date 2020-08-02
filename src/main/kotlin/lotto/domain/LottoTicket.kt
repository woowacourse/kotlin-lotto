package lotto.domain

class LottoTicket(numbers: List<LottoNumber>) {
    companion object {
        const val LOTTO_TICKET_SIZE = 6
    }

    init {
        check(numbers.toSet().size == LOTTO_TICKET_SIZE)
    }

    val lottoNumbers: List<LottoNumber> = numbers.sorted()

    fun countOfSameNumbers(winningNumbers: LottoTicket): Int {
        return winningNumbers.lottoNumbers
            .filter { lottoNumbers.contains(it) }
            .count()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LottoTicket

        if (lottoNumbers != other.lottoNumbers) return false

        return true
    }

    override fun hashCode(): Int {
        return lottoNumbers.hashCode()
    }
}

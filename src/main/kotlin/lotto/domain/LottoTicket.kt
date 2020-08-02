package lotto.domain

data class LottoTicket(val lottoNumbers: List<LottoNumber>) {
    companion object {
        const val LOTTO_TICKET_SIZE = 6

        fun from(lottoNumbers: List<LottoNumber>): LottoTicket {
            return LottoTicket(lottoNumbers.sorted())
        }
    }

    init {
        check(lottoNumbers.toSet().size == LOTTO_TICKET_SIZE)
    }

    fun countOfSameNumbers(winningNumbers: LottoTicket): Int {
        return winningNumbers.lottoNumbers
            .filter { lottoNumbers.contains(it) }
            .count()
    }
}

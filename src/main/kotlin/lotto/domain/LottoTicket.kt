package lotto.domain

const val LOTTO_TICKET_SIZE = 6

data class LottoTicket(val lottoNumbers: List<LottoNumber>) {
    init {
        check(lottoNumbers.toSet().size == LOTTO_TICKET_SIZE)
    }

    fun countOfSameNumbers(lottoTicket: LottoTicket): Int {
        return lottoTicket.lottoNumbers
            .filter { lottoNumbers.contains(it) }
            .count()
    }

    companion object {
        fun from(lottoNumbers: List<LottoNumber>): LottoTicket {
            return LottoTicket(lottoNumbers.sorted())
        }
    }
}

package lotto.domain.model.lottoticket

import lotto.domain.valueobject.LottoNumber

interface LottoTicket {
    val lottoNumbers: Set<LottoNumber>

    fun getSortedLottoNumbers(): List<LottoNumber> = lottoNumbers.sortedBy { it.value }

    companion object {
        const val LOTTO_TICKET_SIZE = 6
    }
}

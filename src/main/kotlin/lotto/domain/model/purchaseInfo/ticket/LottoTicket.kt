package lotto.domain.model.purchaseInfo.ticket

import lotto.domain.model.purchaseInfo.LottoNumber

interface LottoTicket {
    val lottoNumbers: Set<LottoNumber>

    fun getSortedLottoNumbers(): List<LottoNumber> = lottoNumbers.sortedBy { it.value }

    companion object {
        const val LOTTO_TICKET_SIZE = 6
    }
}

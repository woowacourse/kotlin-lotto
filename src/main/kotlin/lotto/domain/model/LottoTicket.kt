package lotto.domain.model

import lotto.domain.valueobject.LottoNumber

interface LottoTicket {
    val lottoNumbers: Set<LottoNumber>

    fun getSortedLottoNumbers(): List<LottoNumber> = lottoNumbers.sortedBy { it.value }

    // todo( fun getRankByWinInfo(winTicketInfo: WinTicketInfo): Rank )

    companion object {
        const val LOTTO_TICKET_SIZE = 6
    }
}

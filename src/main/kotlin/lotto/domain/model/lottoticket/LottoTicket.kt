package lotto.domain.model.lottoticket

import lotto.domain.model.winning.Rank
import lotto.domain.model.winning.WinTicketInfo
import lotto.domain.valueobject.LottoNumber

interface LottoTicket {
    val lottoNumbers: Set<LottoNumber>

    fun getSortedLottoNumbers(): List<LottoNumber> = lottoNumbers.sortedBy { it.value }

    fun getRankByWinInfo(winTicketInfo: WinTicketInfo): Rank {
        val countOfMatch =
            winTicketInfo.winLottoTicket.lottoNumbers
                .intersect(lottoNumbers)
                .size
        val isMatchedBonus = winTicketInfo.bonusNumber in lottoNumbers

        return Rank.valueOf(countOfMatch, isMatchedBonus)
    }

    companion object {
        const val LOTTO_TICKET_SIZE = 6
    }
}

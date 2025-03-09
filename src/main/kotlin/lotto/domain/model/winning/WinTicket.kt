package lotto.domain.model.winning

import lotto.domain.model.purchaseInfo.LottoNumber
import lotto.domain.model.purchaseInfo.ticket.LottoTicket

class WinTicket(
    val winLottoTicket: LottoTicket,
    val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber !in winLottoTicket.lottoNumbers) {
            ERROR_DUPLICATE_BONUS_NUMBER
        }
    }

    fun calculateWinningStatistics(boughtTickets: List<LottoTicket>): Map<Rank, WinningQuantity> {
        val rawValueResult = boughtTickets.groupingBy { getSingleTicketRank(it) }.eachCount()
        return rawValueResult.mapValues { WinningQuantity(it.value) }
    }

    private fun getSingleTicketRank(boughtTicket: LottoTicket): Rank {
        val countOfMatch =
            winLottoTicket.lottoNumbers
                .intersect(boughtTicket.lottoNumbers)
                .size
        val isMatchedBonus = bonusNumber in boughtTicket.lottoNumbers

        return Rank.valueOf(countOfMatch, isMatchedBonus)
    }

    companion object {
        const val ERROR_DUPLICATE_BONUS_NUMBER = "당첨 번호와 보너스 번호는 서로 중복될 수 없습니다."
    }
}

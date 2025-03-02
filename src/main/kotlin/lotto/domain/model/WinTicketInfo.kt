package lotto.domain.model

import lotto.domain.valueobject.LottoNumber

class WinTicketInfo(
    val winLottoTicket: LottoTicket,
    val bonusNumber: LottoNumber,
) {
//    fun calculateLottoRank(boughtLottoTicket: LottoTicket): Rank {
//        val countOfMatch = winLottoTicket.lottoNumbers.intersect(boughtLottoTicket.lottoNumbers).size
//        val isMatchedBonus = bonusNumber in boughtLottoTicket.lottoNumbers
//        return Rank.valueOf(countOfMatch, isMatchedBonus)
//    }

    init {
        require(bonusNumber !in winLottoTicket.lottoNumbers) {
            ERROR_DUPLICATE_BONUS_NUMBER
        }
    }

    companion object {
        val ERROR_DUPLICATE_BONUS_NUMBER = "당첨 번호와 보너스 번호는 서로 중복될 수 없습니다."
    }
}

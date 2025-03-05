package lotto.domain.model.winning

import lotto.domain.model.lottoticket.LottoTicket
import lotto.domain.valueobject.LottoNumber

class WinTicket(
    val winLottoTicket: LottoTicket,
    val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber !in winLottoTicket.lottoNumbers) {
            ERROR_DUPLICATE_BONUS_NUMBER
        }
    }

    companion object {
        val ERROR_DUPLICATE_BONUS_NUMBER = "당첨 번호와 보너스 번호는 서로 중복될 수 없습니다."
    }
}

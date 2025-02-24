package domain.model

import ERROR
import util.LottoFactory

class WinningLotto(
    val lotto: Lotto,
    val bonusNumber: BonusNumber,
) {
    init {
        require(bonusNumber.value !in LottoFactory.extractionNumber(lotto)) { DUPLICATED_BONUS_NUMBER }
    }

    companion object {
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
    }
}

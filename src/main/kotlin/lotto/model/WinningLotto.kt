package lotto.model

import lotto.constants.StringConstants.INVALID_DUPLICATE_BONUS_NUMBER

class WinningLotto(
    val lotto: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber)) { INVALID_DUPLICATE_BONUS_NUMBER }
    }
}

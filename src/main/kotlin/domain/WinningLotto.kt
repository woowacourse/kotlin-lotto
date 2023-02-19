package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO }
    }

    companion object {
        const val ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO = "로또 번호와 보너스 번호는 같을 수 없습니다."
    }
}

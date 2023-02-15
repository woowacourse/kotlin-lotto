package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class WinningLotto(val lotto: Lotto, val bonusNumber: Int) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO }
        require(bonusNumber in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { ERROR_BONUS_NUMBER_RANGE }
    }

    fun getWinningNumbers(): Set<Int> = lotto.numbers

    companion object {
        const val ERROR_BONUS_NUMBER_RANGE = "보너스 번호는 1이상 45이하여야 합니다."
        const val ERROR_CONTAIN_BONUS_NUMBER_IN_LOTTO = "로또 번호와 보너스 번호는 같을 수 없습니다."
    }
}

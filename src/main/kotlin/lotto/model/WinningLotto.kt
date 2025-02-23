package lotto.model

import lotto.model.Lotto.Companion.ERROR_LOTTO_BOUND_MESSAGE
import lotto.model.Lotto.Companion.MAXIMUM_LOTTO_RANGE
import lotto.model.Lotto.Companion.MINIMUM_LOTTO_RANGE

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: Int,
) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) { ERROR_LOTTO_BONUS_DUPLICATED_MESSAGE }
        require(bonusNumber in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { ERROR_LOTTO_BOUND_MESSAGE }
    }

    fun getCountOfMatch(lotto: Lotto): Int = lotto.numbers.count { number -> this.lotto.numbers.contains(number) }

    fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.numbers.contains(this.bonusNumber)

    companion object {
        private const val ERROR_LOTTO_BONUS_DUPLICATED_MESSAGE = "당첨 번호와 보너스 번호는 중복될 수 없습니다."
    }
}

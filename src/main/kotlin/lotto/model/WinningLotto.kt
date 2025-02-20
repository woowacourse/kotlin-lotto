package lotto.model

import lotto.model.Lotto.Companion.MAXIMUM_LOTTO_RANGE
import lotto.model.Lotto.Companion.MINIMUM_LOTTO_RANGE

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: Int,
) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) { "당첨 번호와 보너스 번호는 중복될 수 없습니다." }
        require(bonusNumber in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { "보너스 번호는 1에서 45 범위 내에서 있어야 합니다." }
    }

    fun getCountOfMatch(lotto: Lotto): Int = lotto.numbers.count { number -> this.lotto.numbers.contains(number) }

    fun isBonusNumberMatch(lotto: Lotto): Boolean = lotto.numbers.contains(this.bonusNumber)
}

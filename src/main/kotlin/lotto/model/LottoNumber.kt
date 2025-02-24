package lotto.model

import lotto.model.Lotto.Companion.MAXIMUM_LOTTO_RANGE
import lotto.model.Lotto.Companion.MINIMUM_LOTTO_RANGE

class LottoNumber(
    val number: Int,
) {
    init {
        require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { ERROR_LOTTO_BOUND_MESSAGE }
    }

    companion object {
        const val ERROR_LOTTO_BOUND_MESSAGE = "로또 번호는 1에서 45 범위 내에서 있어야 합니다."
    }
}

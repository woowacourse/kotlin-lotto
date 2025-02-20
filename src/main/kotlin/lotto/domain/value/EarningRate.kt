package lotto.domain.value

import lotto.constants.ErrorMessages
import kotlin.math.roundToInt

class EarningRate(
    rate: Double,
) {
    val rate: Double = rate
        get() = (field * 100.0).roundToInt() / 100.0

    init {
        require(rate >= MINIMUM_EARNING_RATE) { ErrorMessages.INVALID_EARNING_RATE_RANGE }
    }

    companion object {
        private const val MINIMUM_EARNING_RATE = 0.0
    }
}

package lotto.domain.value

import kotlin.math.roundToInt

class EarningRate(
    rate: Double,
) {
    val rate: Double = rate
        get() = (field * 100.0).roundToInt() / 100.0

    init {
        require(rate >= MINIMUM_EARNING_RATE) { INVALID_EARNING_RATE.format(rate) }
    }

    companion object {
        private const val MINIMUM_EARNING_RATE = 0.0
        private const val INVALID_EARNING_RATE = "수익률은 $MINIMUM_EARNING_RATE 이상이어야 합니다. (현재 입력값: %f)"
    }
}

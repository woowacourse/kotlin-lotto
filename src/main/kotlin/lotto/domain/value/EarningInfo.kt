package lotto.domain.value

import lotto.enums.GainLoss
import lotto.enums.GainLoss.GAIN
import lotto.enums.GainLoss.LOSS
import lotto.enums.GainLoss.PRINCIPAL
import kotlin.math.roundToInt

class EarningInfo(
    rate: Double,
) {
    val rate: Double = rate
        get() = (field * 100.0).roundToInt() / 100.0

    val gainLoss: GainLoss
        get() {
            if (rate > 1) return GAIN
            if (rate == 1.0) return PRINCIPAL
            return LOSS
        }

    init {
        require(rate >= MINIMUM_EARNING_RATE) { INVALID_EARNING_RATE.format(rate) }
    }

    companion object {
        private const val MINIMUM_EARNING_RATE = 0.0
        private const val INVALID_EARNING_RATE = "수익률은 $MINIMUM_EARNING_RATE 이상이어야 합니다. (현재 입력값: %f)"
    }
}

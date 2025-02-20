package lotto.domain.value

import lotto.constants.ErrorMessages

@JvmInline
value class EarningRate(
    val rate: Double,
) {
    init {
        require(rate >= MINIMUM_EARNING_RATE) { ErrorMessages.INVALID_EARNING_RATE_RANGE }
    }

    companion object {
        private const val MINIMUM_EARNING_RATE = 0.0
    }
}

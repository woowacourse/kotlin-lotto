package domain.value

class EarningRate(
    val rate: Double,
) {
    init {
        require(rate in MINIMUM_EARNING_RATE..MAXIMUM_EARNING_RATE)
    }

    companion object {
        private const val MINIMUM_EARNING_RATE = 0.0
        private const val MAXIMUM_EARNING_RATE = 1.0
    }
}

package domain.value

class EarningRate(
    val rate: Double,
) {
    init {
        require(rate >= MINIMUM_EARNING_RATE)
    }

    companion object {
        private const val MINIMUM_EARNING_RATE = 0.0
    }
}

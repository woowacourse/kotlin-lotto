package lotto.domain.valueobject

@JvmInline
value class EarningRate(
    val rate: Double,
) {
    init {
        require(rate >= 0.00) { ERROR_NEGATIVE_NUMBER }
    }

    companion object {
        private const val ERROR_NEGATIVE_NUMBER = "수익률은 음수가 될 수 없습니다."
    }
}

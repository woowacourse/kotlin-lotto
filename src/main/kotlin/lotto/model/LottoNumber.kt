package lotto.model

data class LottoNumber(
    val number: Int,
) {
    init {
        require(number in MINIMUM_NUMBER..MAXIMUM_NUMBER) {
            IllegalArgumentException(ERROR_INVALID_RANGE)
        }
    }

    companion object {
        private const val ERROR_INVALID_RANGE = "숫자는 1에서 45 사이여야 합니다."
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
    }
}

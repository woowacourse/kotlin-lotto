package lotto.domain.valueobject

data class LottoNumber(
    val value: Int,
) {
    init {
        require(value in LOTTO_RANGE) { ERROR_NOT_IN_RANGE }
    }

    override fun toString(): String = value.toString()

    companion object {
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        val LOTTO_RANGE = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER

        private const val ERROR_NOT_IN_RANGE = "로또 번호는 ${LOTTO_MIN_NUMBER} 이상 ${LOTTO_MAX_NUMBER} 이하의 정수값이어야 합니다"
    }
}

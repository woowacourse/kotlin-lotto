package lotto.domain.value

@JvmInline
value class LottoNumber(
    val number: Int,
) {
    init {
        require(number in LOTTO_RANGE) { NOT_IN_LOTTO_RANGE_NUMBER_EXITS }
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        val LOTTO_RANGE = MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER

        private val NOT_IN_LOTTO_RANGE_NUMBER_EXITS = "$LOTTO_RANGE 범위 내의 숫자로 입력해주세요."
    }
}

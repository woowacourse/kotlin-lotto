package lotto.domain.value

@JvmInline
value class LottoNumber private constructor(
    val number: Int,
) {
    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val ERROR_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1에서 45 사이여야 합니다."

        val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).associateWith(::LottoNumber)

        fun from(number: Int): LottoNumber = requireNotNull(NUMBERS[number]) { ERROR_LOTTO_NUMBER_RANGE }
    }
}

package lotto.domain.value

import lotto.constants.ErrorMessages

@JvmInline
value class LottoNumber private constructor(
    val number: Int,
) {
    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45

        val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).associateWith(::LottoNumber)

        fun from(number: Int): LottoNumber = requireNotNull(NUMBERS[number]) { ErrorMessages.INVALID_LOTTO_NUMBER_RANGE }
    }
}

package lotto.domain

class LottoNumber private constructor(val number: Int) {

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        private val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).associateWith(::LottoNumber)
        private const val LOTTO_NUMBER_RANGE_ERROR = "로또 번호는 1과 45 사이여야 합니다."

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR)
        }
    }
}

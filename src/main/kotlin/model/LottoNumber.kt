package model

data class LottoNumber private constructor(val number: Int) {

    init {
        require(number in MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER) { LOTTO_NUMBER_RANGE_ERROR_MESSAGE }
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호가 1~45가 아닙니다"

        private val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE)
        }
    }
}

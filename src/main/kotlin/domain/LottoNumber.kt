package domain

class LottoNumber private constructor(private val value: Int) {

    fun getNumber(): Int {
        return value
    }

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1에서 45 사이여야 합니다."
        private val NUMBERS: Map<Int, LottoNumber> = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber = NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE)

        fun getMinNumber(): Int = MINIMUM_NUMBER

        fun getMaxNumber(): Int = MAXIMUM_NUMBER
    }
}

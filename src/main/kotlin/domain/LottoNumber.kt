package domain

class LottoNumber private constructor(number: Int) {

    val number: Int = number
        get() = field

    companion object {
        const val MINIMUM_NUMBER = 1
        const val MAXIMUM_NUMBER = 45
        const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE =
            "로또 번호는 " + MINIMUM_NUMBER + "에서 " + MINIMUM_NUMBER + "사이의 숫자여야 합니다. "
        private val NUMBERS: Map<Int, LottoNumber> = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE)
        }
    }
}

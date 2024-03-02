package model

data class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_NUMBER_RANGE) { ERROR_LOTTO_NUMBER_RANGE }
    }

    constructor(number: String) : this(validateIsDigit(number))

    companion object {
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        private val LOTTO_NUMBER_RANGE: IntRange = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
        private val NUMBERS: Map<Int, LottoNumber> = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).associateWith(::LottoNumber)
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_LOTTO_NUMBER_RANGE = "${ERROR_PREFIX}로또 번호는 1~45 사이여야 합니다."
        private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException()
        }

        private fun validateIsDigit(number: String): Int {
            require(number.all { it.isDigit() }) { ERROR_INPUT_TYPE_MESSAGE }
            return number.toInt()
        }
    }
}

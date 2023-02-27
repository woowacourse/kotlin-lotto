package model

@JvmInline
value class LottoNumber(val number: Int) {

    init {
        require(number in range) { LOTTO_NUMBER_RANGE_ERROR_MESSAGE }
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private val range = MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER
        private const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호가 1~45가 아닙니다"
    }
}

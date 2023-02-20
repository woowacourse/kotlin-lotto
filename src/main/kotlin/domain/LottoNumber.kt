package domain

@JvmInline
value class LottoNumber private constructor(val number: Int) {

    companion object {
        private const val LOTTO_NUMBER_MINIMUM = 1
        private const val LOTTO_NUMBER_MAXIMUM = 45
        private val LOTTO_NUMBER_RANGE = LOTTO_NUMBER_MINIMUM..LOTTO_NUMBER_MAXIMUM

        fun create(number: Int): LottoNumber {
            validateNumber(number)
            return LottoNumber(number)
        }

        private fun validateNumber(number: Int) {
            require(number in LOTTO_NUMBER_RANGE) { "[Error] 로또 넘버는 1~45여야합니다." }
        }
    }
}

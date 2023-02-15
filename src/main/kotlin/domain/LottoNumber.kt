package domain

@JvmInline
value class LottoNumber private constructor(val number: Int) {
    init {
        require(number in LOTTO_NUMBER_RANGE) { "$PREFIX 로또 넘버는 1~45여야합니다." }
    }

    companion object {
        const val PREFIX = "[Error]"
        private const val LOTTO_NUMBER_MINIMUM = 1
        private const val LOTTO_NUMBER_MAXIMUM = 45
        val LOTTO_NUMBER_RANGE = LOTTO_NUMBER_MINIMUM..LOTTO_NUMBER_MAXIMUM

        fun create(number: Int): LottoNumber {
            return LottoNumber(number)
        }
    }
}

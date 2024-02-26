package model

@JvmInline
value class GeneralLottoNumber(override val number: Int) : LottoNumber {
    init {
        require(number in LOTTO_NUM_RANGE) { EXCEPTION_NUM_RANGE }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val EXCEPTION_NUM_RANGE = "로또 번호는 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER}까지만 가능합니다."
        private val LOTTO_NUM_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
    }
}

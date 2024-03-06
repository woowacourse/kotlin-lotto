package lotto.model

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
            INVALID_LOTTO_NUMBER_OUT_OF_RANGE
        }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER: Int = 1
        private const val MAX_LOTTO_NUMBER: Int = 45
        private const val INVALID_LOTTO_NUMBER_OUT_OF_RANGE: String =
            "로또 번호가 $MIN_LOTTO_NUMBER~$MAX_LOTTO_NUMBER 사이의 숫자가 아닌 경우 예외처리한다."
    }
}

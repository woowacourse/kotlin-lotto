package lotto.model

class LottoNumber(val number: Int) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
            IllegalArgumentException(INVALID_LOTTO_NUMBER_OUT_OF_RANGE)
        }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        const val INVALID_LOTTO_NUMBER_OUT_OF_RANGE =
            ("로또 번호가 $MIN_LOTTO_NUMBER~$MAX_LOTTO_NUMBER 사이의 숫자가 아닌 경우 예외처리한다.")
    }
}

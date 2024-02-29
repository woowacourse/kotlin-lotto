package lotto.model

data class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) {
            INVALID_LOTTO_NUMBER_RANGE.format(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
        }
    }

    override fun toString() = number.toString()

    companion object {
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        private const val INVALID_LOTTO_NUMBER_RANGE = "%d부터 %d 사이의 숫자를 입력해 주세요."
    }
}

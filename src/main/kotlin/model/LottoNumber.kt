package model

data class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_NUMBER_RANGE) { ERROR_LOTTO_NUMBER_RANGE }
    }

    companion object {
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        val LOTTO_NUMBER_RANGE: IntRange = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
        const val ERROR_LOTTO_NUMBER_RANGE = "로또 번호는 1~45 사이여야 합니다."
    }
}

package lotto.model

data class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_NUMBER_RANGE) { LOTTO_RANGE_ERROR_MESSAGE }
    }

    companion object {
        private const val LOTTO_RANGE_ERROR_MESSAGE = "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        private val LOTTO_NUMBER_RANGE = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
    }
}

package lotto.model

data class LottoNumber(val number: String) {
    init {
        require(number.toIntOrNull() in LOTTO_NUMBER_RANGE) { LOTTO_RANGE_ERROR_MESSAGE }
    }

    companion object {
        private val LOTTO_NUMBER_RANGE: IntRange = 1..45

        private const val LOTTO_RANGE_ERROR_MESSAGE = "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."
    }
}

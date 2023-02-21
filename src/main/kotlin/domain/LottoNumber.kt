package domain

data class LottoNumber(val number: Int) {
    init {
        require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { ERROR_LOTTO_RANGE }
    }

    companion object {
        private const val MINIMUM_LOTTO_RANGE = 1
        private const val MAXIMUM_LOTTO_RANGE = 45
        private const val ERROR_LOTTO_RANGE = "로또 번호는 1이상 45이하여야 합니다."

        private val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE).associateWith(::LottoNumber)

        fun all(): List<LottoNumber> = NUMBERS.values.toList()
    }
}

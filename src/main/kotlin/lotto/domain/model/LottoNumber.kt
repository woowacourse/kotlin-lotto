package lotto.domain.model

data class LottoNumber(val number: Int) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { INVALID_LOTTO_NUMBER_RANGE_MESSAGE }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val INVALID_LOTTO_NUMBER_RANGE_MESSAGE = "로또의 각 번호는 1~45이하의 숫자를 가진다."

        private val NUMBERS: Map<Int, LottoNumber> = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE_MESSAGE)
        }
    }
}

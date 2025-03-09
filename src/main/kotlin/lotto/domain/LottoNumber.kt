package lotto.domain

data class LottoNumber private constructor(val number: Int) {
    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val ERROR_OUT_OF_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1~45 사이어야 합니다. 다시 입력해주세요."

        private val NUMBERS: MutableMap<Int, LottoNumber> = mutableMapOf()

        fun from(number: Int): LottoNumber {
            if (number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
                return NUMBERS.getOrPut(number) { LottoNumber(number) }
            }
            throw IllegalArgumentException(ERROR_OUT_OF_LOTTO_NUMBER_RANGE)
        }
    }
}

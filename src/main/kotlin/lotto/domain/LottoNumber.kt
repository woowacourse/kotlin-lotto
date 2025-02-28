package lotto.domain

class LottoNumber private constructor(val number: Int) {
    companion object {
        private const val ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 1~45 사이어야 합니다."

        const val MAX_BOUND: Int = 45
        const val MIN_BOUND: Int = 1
        private val NUMBERS: Map<Int, LottoNumber> = (MIN_BOUND..MAX_BOUND).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(ERROR_OUT_OF_RANGE)
        }
    }
}

package domain.model.lotto

class LottoNumber private constructor(private val value: Int) {
    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val NUMBER_RANGE_ERROR = "[ERROR] 번호는 1 이상 45 이하입니다."
        private val NUMBERS: Map<Int, LottoNumber> = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(NUMBER_RANGE_ERROR)
        }
    }
}

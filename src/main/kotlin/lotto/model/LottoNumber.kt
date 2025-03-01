package lotto.model

@JvmInline
value class LottoNumber private constructor(
    val number: Int,
) {
    init {
        require(number in MIN_NUMBER..MAX_NUMBER) { ERROR_OUT_OF_RANGE_NUMBERS }
    }

    companion object {
        private const val ERROR_OUT_OF_RANGE_NUMBERS = "로또 번호는 1 ~ 45 사이의 숫자입니다."
        const val MIN_NUMBER = 1
        const val MAX_NUMBER = 45

        private val cachedLottoNumber = (MIN_NUMBER..MAX_NUMBER).associateWith { number -> LottoNumber(number) }

        fun from(number: Int): LottoNumber = cachedLottoNumber[number] ?: throw IllegalArgumentException(ERROR_OUT_OF_RANGE_NUMBERS)
    }
}

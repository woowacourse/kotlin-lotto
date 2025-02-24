package lotto.model

class LottoNumber private constructor(
    val number: Int,
) {
    companion object {
        private const val LOTTO_NUMBER_MIN_RANGE = 1
        private const val LOTTO_NUMBER_MAX_RANGE = 45

        val ALL_LOTTO_NUMBERS = (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE)
        private val CACHE = ALL_LOTTO_NUMBERS.associateWith { LottoNumber(it) }

        fun from(number: Int): LottoNumber =
            CACHE[number]
                ?: throw IllegalArgumentException("[ERROR] 로또 번호의 범위는 $LOTTO_NUMBER_MIN_RANGE 이상 $LOTTO_NUMBER_MAX_RANGE 이하여야 합니다.")
    }
}

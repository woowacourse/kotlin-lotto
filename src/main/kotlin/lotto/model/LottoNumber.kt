package lotto.model

class LottoNumber private constructor(
    val value: Int,
) {
    companion object {
        private const val LOTTO_NUMBER_MIN_RANGE = 1
        private const val LOTTO_NUMBER_MAX_RANGE = 45

        private val CACHE_LOTTO_NUMBER =
            (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE).associateWith { LottoNumber(it) }

        val cachedLottoNumbers: List<LottoNumber> by lazy { CACHE_LOTTO_NUMBER.values.toList() }

        fun from(number: Int): LottoNumber =
            CACHE_LOTTO_NUMBER[number]
                ?: throw IllegalArgumentException("[ERROR] 로또 번호의 범위는 $LOTTO_NUMBER_MIN_RANGE 이상 $LOTTO_NUMBER_MAX_RANGE 이하여야 합니다.")
    }
}

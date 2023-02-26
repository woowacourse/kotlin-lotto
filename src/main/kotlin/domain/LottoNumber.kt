package domain

@JvmInline
value class LottoNumber private constructor(private val number: Int) {
    fun toInt(): Int = number

    companion object {
        const val MINIMUM_LOTTO_RANGE = 1
        const val MAXIMUM_LOTTO_RANGE = 45
        private const val ERROR_LOTTO_RANGE =
            "[ERROR] 현재의 숫자는 %d, 로또의 숫자는 ${MINIMUM_LOTTO_RANGE}에서 ${MAXIMUM_LOTTO_RANGE}사이의 숫자여야 합니다."
        private val lottoNumbers: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE).associateWith { LottoNumber(it) }

        fun from(number: Int): LottoNumber {
            requireNotNull(lottoNumbers[number]) { ERROR_LOTTO_RANGE.format(number) }
            return lottoNumbers[number]!!
        }
    }
}

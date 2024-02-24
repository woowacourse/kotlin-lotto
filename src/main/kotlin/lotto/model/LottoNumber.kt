package lotto.model

@JvmInline
value class LottoNumber private constructor(private val number: Int) {
    override fun toString(): String = number.toString()

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val LOTTO_RANGE_ERROR_MESSAGE =
            "로또의 숫자들은 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER}까지의 숫자로 구성되어야 합니다."

        val LOTTO_NUMBER_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER

        private val cache = LOTTO_NUMBER_RANGE.associateWith { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            return cache[number] ?: throw IllegalArgumentException(LOTTO_RANGE_ERROR_MESSAGE)
        }
    }
}

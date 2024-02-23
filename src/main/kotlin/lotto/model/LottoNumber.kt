package lotto.model

class LottoNumber private constructor(val number: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LottoNumber

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }

    companion object {
        private const val LOTTO_RANGE_ERROR_MESSAGE = "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        val LOTTO_NUMBER_RANGE = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER

        private val cache = (LOTTO_NUMBER_RANGE).associateWith { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            require(number in LOTTO_NUMBER_RANGE) { LOTTO_RANGE_ERROR_MESSAGE }
            return cache[number]!!
        }
    }
}

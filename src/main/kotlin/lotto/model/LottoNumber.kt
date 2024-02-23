package lotto.model

class LottoNumber private constructor(private val number: Int) {
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

    override fun toString(): String {
        return number.toString()
    }

    companion object {
        private const val LOTTO_RANGE_ERROR_MESSAGE = "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        val LOTTO_NUMBER_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER

        private val cache = (LOTTO_NUMBER_RANGE).associateWith { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            require(number in LOTTO_NUMBER_RANGE) { LOTTO_RANGE_ERROR_MESSAGE }
            return cache[number]!!
        }
    }
}

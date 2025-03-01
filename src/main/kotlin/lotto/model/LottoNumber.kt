package lotto.model

class LottoNumber private constructor(private val value: Int) {
    override fun toString(): String = value.toString()

    companion object {
        const val MAXIMUM_LOTTO_RANGE: Int = 45
        const val MINIMUM_LOTTO_RANGE: Int = 1
        const val LOTTO_BOUND_MESSAGE = "로또 번호는 1에서 45 범위 내에서 있어야 합니다."

        private val NUMBERS: Map<Int, LottoNumber> =
            (MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE).associateWith { LottoNumber(it) }

        fun from(value: Int): LottoNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(LOTTO_BOUND_MESSAGE)
        }
    }
}

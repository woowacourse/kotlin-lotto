package domain

@JvmInline
value class LottoNumber private constructor(private val number: Int) : Comparable<LottoNumber> {
    init {
        require(number in MINIMUM_NUMBER..MAXIMUM_NUMBER) { ERROR_NUMBER_IN_RANGE.format(number) }
    }

    fun toInt() = number
    override fun compareTo(other: LottoNumber): Int = this.number - other.number

    companion object {
        const val MINIMUM_NUMBER = 1
        const val MAXIMUM_NUMBER = 45

        private const val ERROR_NUMBER_IN_RANGE = "로또 숫자는 ${MINIMUM_NUMBER}부터 ${MAXIMUM_NUMBER}사이어야합니다. \n잘못된 값: %d"
        private val NUMBERS = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith { LottoNumber(it) }
        fun valueOf(number: Int) = NUMBERS[number] ?: LottoNumber(number)
    }
}

package domain

@JvmInline
value class LottoNumber private constructor(private val number: Int) {
    init {
        require(number in MINIMUM_NUMBER..MAXIMUM_NUMBER) { ERROR_NUMBER_IN_RANGE.format(number) }
    }

    fun toInt(): Int = number

    override fun toString(): String = number.toString()

    companion object {
        const val MINIMUM_NUMBER = 1
        const val MAXIMUM_NUMBER = 45
        val NUMBERS: Map<Int, LottoNumber> = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith(::LottoNumber)

        fun from(number: Int): LottoNumber {
            return NUMBERS[number] ?: throw IllegalArgumentException(ERROR_NUMBER_IN_RANGE.format(number))
        }

        private const val ERROR_NUMBER_IN_RANGE = "로또 숫자는 1부터 45사이어야합니다. \n 잘못된 값: %d"
    }
}

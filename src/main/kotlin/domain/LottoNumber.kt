package domain

class LottoNumber private constructor(val number: Int) {
    init {
        require(number in MINIMUM_NUMBER..MAXIMUM_NUMBER) { ERROR_NUMBER_IN_RANGE.format(number) }
    }

    override fun equals(other: Any?): Boolean = if (other is LottoNumber) this.number == other.number else false
    override fun hashCode(): Int = this.number.hashCode()

    override fun toString() = number.toString()

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45

        private const val ERROR_NUMBER_IN_RANGE = "로또 숫자는 ${MINIMUM_NUMBER}부터 ${MAXIMUM_NUMBER}사이어야합니다. \n잘못된 값: %d"
        private val NUMBERS = (MINIMUM_NUMBER..MAXIMUM_NUMBER).associateWith { LottoNumber(it) }
        fun valueOf(number: Int) = NUMBERS[number] ?: LottoNumber(number)
    }
}

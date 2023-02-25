package lotto.domain

data class LotteryNumber(
    private val number: Int
) {
    init {
        checkNumberBoundary()
    }

    fun toInt(): Int = number

    override fun toString(): String = number.toString()

    private fun checkNumberBoundary() {
        require(number in LOWER_BOUNDARY..UPPER_BOUNDARY) { ERROR_MESSAGE_BOUNDARY }
    }

    companion object {
        const val LOWER_BOUNDARY = 1
        const val UPPER_BOUNDARY = 45

        private const val ERROR_MESSAGE_BOUNDARY = "로또 번호는 1 이상 45 이하의 숫자여야 합니다."

        private val NUMBERS: Map<Int, LotteryNumber> =
            (LOWER_BOUNDARY..UPPER_BOUNDARY).associateWith(::LotteryNumber)

        fun from(number: Int): LotteryNumber {
            return NUMBERS[number] ?: throw IllegalArgumentException()
        }
    }
}

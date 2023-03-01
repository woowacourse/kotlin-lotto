package lotto.domain

@JvmInline
value class LotteryNumber(
    private val value: Int
) {
    init {
        checkNumberBoundary()
    }

    fun toInt(): Int = value

    override fun toString(): String = value.toString()

    private fun checkNumberBoundary() {
        require(value in LOWER_BOUNDARY..UPPER_BOUNDARY) { ERROR_MESSAGE_BOUNDARY }
    }

    companion object {
        const val LOWER_BOUNDARY = 1
        const val UPPER_BOUNDARY = 45

        private const val ERROR_MESSAGE_BOUNDARY = "로또 번호는 1 이상 45 이하의 숫자여야 합니다."
    }
}

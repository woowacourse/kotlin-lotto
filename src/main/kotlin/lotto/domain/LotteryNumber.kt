package lotto.domain

data class LotteryNumber(
    private val number: Int
) {
    init {
        checkNumberBoundary()
    }

    fun toInt(): Int = number

    private fun checkNumberBoundary() {
        require(number in LOTTERY_NUMBER_LOWER_BOUNDARY..LOTTERY_NUMBER_UPPER_BOUNDARY) {
            "로또 번호는 1 이상 45 이하의 숫자여야 합니다."
        }
    }

    companion object {
        const val LOTTERY_NUMBER_LOWER_BOUNDARY = 1
        const val LOTTERY_NUMBER_UPPER_BOUNDARY = 45
    }
}

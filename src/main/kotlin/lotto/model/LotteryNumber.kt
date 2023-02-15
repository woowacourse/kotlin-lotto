package lotto.model

class LotteryNumber(
    val number: Int
) {
    init {
        checkNumberBoundary()
    }

    private fun checkNumberBoundary() {
        require(number in LOTTERY_NUMBER_LOWER_BOUNDARY..LOTTERY_NUMBER_UPPER_BOUNDARY) {
            "로또 번호는 1 이상 45 이하의 숫자여야 합니다."
        }
    }

    companion object {
        private const val LOTTERY_NUMBER_LOWER_BOUNDARY = 1
        private const val LOTTERY_NUMBER_UPPER_BOUNDARY = 45
    }
}

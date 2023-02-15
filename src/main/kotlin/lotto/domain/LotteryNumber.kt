package lotto.domain

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LotteryNumber

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }

    companion object {
        private const val LOTTERY_NUMBER_LOWER_BOUNDARY = 1
        private const val LOTTERY_NUMBER_UPPER_BOUNDARY = 45
    }
}

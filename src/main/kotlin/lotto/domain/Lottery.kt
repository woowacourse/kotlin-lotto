package lotto.domain

class Lottery(
    val numbers: List<LotteryNumber>
) {
    init {
        checkLotteryNumbersSize()
    }

    private fun checkLotteryNumbersSize() {
        require(numbers.size == LOTTERY_NUMBER_SIZE) { LOTTERY_NUMBER_SIZE_ERROR }
    }

    companion object {
        private const val LOTTERY_NUMBER_SIZE = 6
        private const val LOTTERY_NUMBER_SIZE_ERROR = "로또 번호는 6개여야 합니다."
    }
}

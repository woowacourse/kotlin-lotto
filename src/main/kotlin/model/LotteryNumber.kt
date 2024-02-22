package model

data class LotteryNumber(val number: Int) {
    init {
        require(number in LOTTERY_NUMBER_RANGE) { ERROR_LOTTERY_OUT_OF_RANGE }
    }

    companion object {
        fun bonusNumber(
            winningLottery: Lottery,
            input: String,
        ): LotteryNumber {
            val number = input.toIntOrNull()
            requireNotNull(number) { "숫자를 입력하세요" }

            require(number in LOTTERY_NUMBER_RANGE) { ERROR_LOTTERY_OUT_OF_RANGE }
            require(!winningLottery.lotteryNumbers.contains(LotteryNumber(number)))
            return LotteryNumber(number)
        }

        private const val MIN_LOTTERY_NUMBER = 1
        private const val MAX_LOTTERY_NUMBER = 45

        private val LOTTERY_NUMBER_RANGE: IntRange = MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER

        private const val ERROR_LOTTERY_OUT_OF_RANGE = "로또 번호는 $MIN_LOTTERY_NUMBER 이상 $MAX_LOTTERY_NUMBER 이하의 숫자여야 합니다."
    }
}

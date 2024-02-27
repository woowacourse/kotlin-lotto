package model.lottery

data class LotteryNumber(val number: Int) {
    init {
        require(number in LOTTERY_NUMBER_RANGE) { ERROR_LOTTERY_OUT_OF_RANGE }
    }

    companion object {
        private const val MIN_LOTTERY_NUMBER = 1
        private const val MAX_LOTTERY_NUMBER = 45
        private val LOTTERY_NUMBER_RANGE: IntRange = MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER

        private val NUMBERS: Map<Int, LotteryNumber> =
            LOTTERY_NUMBER_RANGE.associateWith(::LotteryNumber)

        fun from(input: String): LotteryNumber {
            val number = input.toIntOrNull()
            requireNotNull(number)
            return NUMBERS[number] ?: throw IllegalArgumentException("올바르지 않은 번호입니다.")
        }

        private const val ERROR_LOTTERY_OUT_OF_RANGE = "로또 번호는 $MIN_LOTTERY_NUMBER 이상 $MAX_LOTTERY_NUMBER 이하의 숫자여야 합니다."
    }
}

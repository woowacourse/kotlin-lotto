package model

class Lottery private constructor(val lotteryNumbers: List<LotteryNumber>) {
    init {
        val lotteryNumberCount = lotteryNumbers.size
        require(lotteryNumberCount == LOTTERY_NUMBER_COUNT) { ERROR_INVALID_LOTTERY_NUMBER_COUNT }
        require(lotteryNumbers.toSet().size == lotteryNumberCount) { ERROR_INVALID_LOTTERY_DUPLICATED }
    }

    companion object {
        private const val LOTTERY_NUMBER_COUNT = 6

        private const val COMMA = ","

        private const val ERROR_INVALID_LOTTERY_NUMBER_COUNT = "로또 번호가 ${LOTTERY_NUMBER_COUNT}개가 아닙니다."
        private const val ERROR_INVALID_LOTTERY_DUPLICATED = "로또에 중복된 번호가 있습니다."
        private const val ERROR_BLANK_INPUT = "공백을 입력하셨습니다."

        fun of(vararg numbers: Int): Lottery = Lottery(numbers.map { LotteryNumber(it) }.toList())

        fun fromInput(input: String): Lottery {
            require(input.replace(" ", "").isNotEmpty()) { ERROR_BLANK_INPUT }
            val sortedNumbers = input.replace(" ", "").split(COMMA).map { it.toInt() }.sorted()
            val numbers = sortedNumbers.map { LotteryNumber(it) }
            return Lottery(numbers)
        }
    }
}

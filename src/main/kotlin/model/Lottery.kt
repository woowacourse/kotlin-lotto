package model

class Lottery private constructor(val lotteryNumbers: List<LotteryNumber>) {
    init {
        val lotteryNumberCount = lotteryNumbers.size
        require(lotteryNumberCount == LOTTERY_NUMBER_COUNT) { ERROR_INVALID_LOTTERY_NUMBER_COUNT }
        require(lotteryNumbers.toSet().size == lotteryNumberCount) { ERROR_INVALID_LOTTERY_DUPLICATED }
    }

    companion object {
        private const val LOTTERY_NUMBER_COUNT = 6
        private const val ERROR_INVALID_LOTTERY_NUMBER_COUNT = "로또 번호가 ${LOTTERY_NUMBER_COUNT}개가 아닙니다."
        private const val ERROR_INVALID_LOTTERY_DUPLICATED = "로또에 중복된 번호가 있습니다."

        fun of(vararg numbers: Int): Lottery = Lottery(numbers.map { LotteryNumber(it) }.toList())
    }
}

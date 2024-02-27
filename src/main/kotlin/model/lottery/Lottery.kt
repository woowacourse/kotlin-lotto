package model.lottery

import model.Quantity

class Lottery private constructor(val lotteryNumbers: List<LotteryNumber>) {
    init {
        val lotteryNumberCount = lotteryNumbers.size
        require(lotteryNumberCount == LOTTERY_NUMBER_COUNT) { ERROR_INVALID_LOTTERY_NUMBER_COUNT }
        require(lotteryNumbers.toSet().size == lotteryNumberCount) { ERROR_INVALID_LOTTERY_DUPLICATED }
    }

    fun compareLottery(other: Lottery): Quantity = Quantity(lotteryNumbers.count { it in other.lotteryNumbers })

    fun contains(number: LotteryNumber): Boolean = lotteryNumbers.any { it == number }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lottery

        if (lotteryNumbers != other.lotteryNumbers) return false

        return true
    }

    override fun hashCode(): Int {
        return lotteryNumbers.hashCode()
    }

    override fun toString(): String {
        return "Lottery(lotteryNumbers=$lotteryNumbers)"
    }

    companion object {
        private const val LOTTERY_NUMBER_COUNT = 6
        private const val ERROR_INVALID_LOTTERY_NUMBER_COUNT = "로또 번호가 ${LOTTERY_NUMBER_COUNT}개가 아닙니다."
        private const val ERROR_INVALID_LOTTERY_DUPLICATED = "로또에 중복된 번호가 있습니다."

        fun of(vararg numbers: Int): Lottery = Lottery(numbers.map { LotteryNumber(it) }.toList())

        fun fromInput(input: String): Lottery {
            require(input.isNotBlank()) { "공백을 입력하셨습니다." }
            val numbers = input.split(",").map { LotteryNumber(it.toInt()) }
            return Lottery(numbers)
        }
    }
}

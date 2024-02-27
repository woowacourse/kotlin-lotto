package model

class Lottery(private val lotteryNumbers: List<LotteryNumber>) {
    init {
        require(validateCount(lotteryNumbers)) { EXCEPTION_INVALID_COUNT }
        require(validateDuplicate(lotteryNumbers)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    fun getCountOfMatch(lottery: Lottery) = lotteryNumbers.intersect(lottery.lotteryNumbers).size

    fun hasLotteryNumber(lotteryNumber: LotteryNumber) = lotteryNumbers.contains(lotteryNumber)

    fun hasBonus(bonus: Bonus) = lotteryNumbers.contains(bonus.lotteryNumber)

    override fun toString() = "[${lotteryNumbers.map { it.number }.joinToString(", ")}]\n"

    private fun validateCount(lotteryNumbers: List<LotteryNumber>) = lotteryNumbers.size == LOTTERY_COUNT

    private fun validateDuplicate(lotteryNumbers: List<LotteryNumber>) = lotteryNumbers.size == lotteryNumbers.toSet().size

    companion object {
        private const val LOTTERY_COUNT = 6
        private const val LOTTERY_DELIMITER = ","
        const val EXCEPTION_INVALID_COUNT = "로또 번호는 ${LOTTERY_COUNT}개여야 합니다"
        const val EXCEPTION_DUPLICATED_NUMBER = "로또 번호에 중복이 없어야 합니다"

        fun fromList(numbers: List<Int>): Lottery {
            return numbers.map { LotteryNumber(it) }.run { Lottery(this) }
        }

        fun fromInput(input: String): Lottery {
            return input.split(LOTTERY_DELIMITER).map {
                LotteryNumber.fromInput(it)
            }.run {
                Lottery(this)
            }
        }
    }
}

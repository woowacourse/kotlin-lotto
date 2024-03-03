package lotto.model.lottery

class Lottery(
    private val lotteryNumbers: Set<LotteryNumber>,
    val isManual: Boolean = true,
) {
    init {
        require(validateDuplicate(lotteryNumbers)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    private fun validateDuplicate(lotteryNumbers: Set<LotteryNumber>) = lotteryNumbers.size == LOTTERY_COUNT

    fun getCountOfMatch(lottery: Lottery) = lotteryNumbers.intersect(lottery.lotteryNumbers).size

    fun hasLotteryNumber(lotteryNumber: LotteryNumber) = lotteryNumbers.contains(lotteryNumber)

    fun hasBonus(bonus: Bonus) = lotteryNumbers.contains(bonus.lotteryNumber)

    fun toString(delimiter: String) = lotteryNumbers.joinToString(delimiter) { it.toString() }

    companion object {
        private const val LOTTERY_COUNT = 6
        const val EXCEPTION_INVALID_COUNT = "로또 번호는 ${LOTTERY_COUNT}개여야 합니다"
        const val EXCEPTION_DUPLICATED_NUMBER = "로또 번호에 중복이 없어야 합니다"

        private fun List<String>.validateCount(): List<String> {
            require(this.size == LOTTERY_COUNT) { EXCEPTION_INVALID_COUNT }
            return this
        }

        fun fromSet(numbers: Set<Int>): Lottery {
            val lotteryNumbers = numbers.map { LotteryNumber.from(it) }.toSet()
            return Lottery(lotteryNumbers)
        }

        fun fromInput(input: List<String>): Lottery {
            val lotteryNumbers = input.validateCount().map { LotteryNumber.fromInput(it) }.toSet()
            return Lottery(lotteryNumbers)
        }
    }
}

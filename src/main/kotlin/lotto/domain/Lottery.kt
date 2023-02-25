package lotto.domain

class Lottery(val numbers: List<LotteryNumber>) {
    init {
        checkLotteryNumbersSize()
        checkNumbersDuplicate()
    }

    constructor(vararg numbers: Int) : this(numbers.map { LotteryNumber.from(it) }.toList())

    override fun toString(): String = numbers.sortedBy { it.toInt() }.toString()

    fun countMatches(lottery: Lottery): Int = lottery.numbers.count { numbers.contains(it) }

    fun contains(lotteryNumber: LotteryNumber): Boolean = numbers.contains(lotteryNumber)

    private fun checkLotteryNumbersSize() {
        require(numbers.size == NUMBER_SIZE) { ERROR_MESSAGE_NUMBER_SIZE }
    }

    private fun checkNumbersDuplicate() {
        require(numbers.size == numbers.distinct().size) { ERROR_MESSAGE_DUPLICATE }
    }

    companion object {
        private const val ERROR_MESSAGE_DUPLICATE = "6개의 로또번호는 서로 중복되지 않아야 합니다."
        private const val ERROR_MESSAGE_NUMBER_SIZE = "로또 번호는 6개여야 합니다."
        const val NUMBER_SIZE = 6
    }
}

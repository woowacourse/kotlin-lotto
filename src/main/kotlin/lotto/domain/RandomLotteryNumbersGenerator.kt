package lotto.domain

class RandomLotteryNumbersGenerator : LotteryNumbersGenerator {
    private val numbers = (LOWER_BOUND..UPPER_BOUND).toList()

    override fun generate(): List<Int> {
        return numbers.shuffled().subList(FROM_INDEX, TO_INDEX)
    }

    companion object {
        private const val FROM_INDEX = 0
        private const val LOWER_BOUND = 1
        private const val TO_INDEX = 6
        private const val UPPER_BOUND = 45
    }
}

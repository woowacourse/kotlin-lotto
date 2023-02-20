package lotto.domain

class RandomLotteryGenerator : LotteryGenerator {
    private val numbers = (LOWER_BOUND..UPPER_BOUND).toList()

    override fun generate(): Lottery {
        return Lottery(numbers.shuffled().subList(FROM_INDEX, TO_INDEX).map { LotteryNumber.from(it) })
    }

    companion object {
        private const val FROM_INDEX = 0
        private const val LOWER_BOUND = 1
        private const val TO_INDEX = 6
        private const val UPPER_BOUND = 45
    }
}

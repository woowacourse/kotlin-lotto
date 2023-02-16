package lotto.domain

class RandomLotteryNumberGenerator : LotteryNumberGenerator {
    private val numbers = (LOWER_BOUND..UPPER_BOUND).toList()

    override fun generate(): Int {
        return numbers.shuffled().first()
    }

    companion object {
        private const val LOWER_BOUND = 1
        private const val UPPER_BOUND = 45
    }
}

package utils

import model.LotteryNumber

object RandomLotteryNumberGenerator : LotteryNumberGenerator {
    private const val MIN_LOTTERY_NUMBER = 1
    private const val MAX_LOTTERY_NUMBER = 45
    private const val LOTTERY_COUNT = 6

    override fun generateNumbers(): Set<LotteryNumber> =
        (MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER)
            .shuffled()
            .take(LOTTERY_COUNT)
            .sorted()
            .map {
                LotteryNumber.from(it)
            }.toSet()
}

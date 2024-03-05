package utils

import model.LotteryNumber

object RandomLotteryGenerator {
    private const val MIN_LOTTO_NUMBER = 1
    private const val MAX_LOTTO_NUMBER = 45
    private const val LOTTO_COUNT = 6

    fun generateNumbers(): List<LotteryNumber> =
        (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
            .toList()
            .shuffled()
            .take(LOTTO_COUNT)
            .sorted()
            .map {
                LotteryNumber(it)
            }
}

package model

class LotteryGenerator {
    fun generate(): Lottery =
        Lottery.of(
            *(MIN_LOTTERY_NUMBER..MAX_LOTTERY_NUMBER)
                .shuffled()
                .take(LOTTERY_NUMBER_COUNT)
                .sorted()
                .toIntArray(),
        )

    companion object {
        private const val MIN_LOTTERY_NUMBER = 1
        private const val MAX_LOTTERY_NUMBER = 45
        private const val LOTTERY_NUMBER_COUNT = 6
    }
}

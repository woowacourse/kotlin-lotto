package lotto.domain

import kotlin.random.Random.Default.nextInt

class LotteryNumberGenerator {

    fun generateLotteryNumber(): LotteryNumber {
        val randomNumber = nextInt(LOTTERY_NUMBER_LOWER_BOUNDARY, LOTTERY_NUMBER_UPPER_BOUNDARY + 1)
        return LotteryNumber(randomNumber)
    }

    companion object {
        private const val LOTTERY_NUMBER_LOWER_BOUNDARY = 1
        private const val LOTTERY_NUMBER_UPPER_BOUNDARY = 45
    }
}

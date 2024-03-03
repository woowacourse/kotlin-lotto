package model.lottery.generator

import model.lottery.Lottery.Companion.LOTTERY_NUMBER_COUNT
import model.lottery.LotteryNumber.Companion.LOTTERY_NUMBER_RANGE

object RandomNumbersGenerator : NumbersGenerator {
    override fun generate(): List<Int> = LOTTERY_NUMBER_RANGE.shuffled().take(LOTTERY_NUMBER_COUNT).sorted()
}

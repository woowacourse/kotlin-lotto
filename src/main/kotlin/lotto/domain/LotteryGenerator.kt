package lotto.domain

import lotto.domain.LotteryNumber.Companion.LOTTERY_NUMBER_UPPER_BOUNDARY

class LotteryGenerator {

    fun generateLotteries(count: Int): List<Lottery> {
        val randomLotteries = mutableListOf<Lottery>()

        repeat(count) {
            randomLotteries.add(generateLottery())
        }

        return randomLotteries
    }

    private fun generateLottery(): Lottery {
        var randomLotteryNumberCandidates: MutableList<LotteryNumber> =
            MutableList(LOTTERY_NUMBER_UPPER_BOUNDARY) { i -> LotteryNumber(i + 1) }
        randomLotteryNumberCandidates = randomLotteryNumberCandidates.shuffled().toMutableList()
        val randomLotteryNumbers = mutableListOf<LotteryNumber>()

        repeat(Lottery.LOTTERY_NUMBER_SIZE) {
            randomLotteryNumbers.add(randomLotteryNumberCandidates[it])
            randomLotteryNumberCandidates.removeAt(0)
        }

        return Lottery(randomLotteryNumbers)
    }
}

package lotto.domain

import lotto.domain.LotteryNumber.Companion.UPPER_BOUNDARY

class LotteryGenerator {

    fun generateLotteries(count: Int): List<Lottery> {
        val randomLotteries: MutableList<Lottery> = mutableListOf()

        repeat(count) {
            randomLotteries.add(generateLottery())
        }

        return randomLotteries
    }

    private fun generateLottery(): Lottery {
        val randomLotteryNumberCandidates: List<LotteryNumber> =
            List(UPPER_BOUNDARY) { i -> LotteryNumber(i + 1) }.shuffled()

        return Lottery(randomLotteryNumberCandidates.subList(0, Lottery.NUMBER_SIZE))
    }
}

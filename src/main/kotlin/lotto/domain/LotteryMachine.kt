package lotto.domain

class LotteryMachine() {

    fun createLotteries(lotteries: Lotteries, autoNumber: Int): Lotteries =
        lotteries.plus(generateRandomLotteries(autoNumber))

    fun generateRandomLotteries(count: Int): Lotteries {
        val randomLotteries: MutableList<Lottery> = mutableListOf()
        repeat(count) { randomLotteries.add(generateRandomLottery()) }

        return Lotteries(randomLotteries)
    }

    private fun generateRandomLottery(): Lottery {
        val randomLotteryNumberCandidates: List<LotteryNumber> =
            List(LotteryNumber.UPPER_BOUNDARY) { i -> LotteryNumber(i + 1) }.shuffled()

        return Lottery(randomLotteryNumberCandidates.subList(0, Lottery.NUMBER_SIZE))
    }
}

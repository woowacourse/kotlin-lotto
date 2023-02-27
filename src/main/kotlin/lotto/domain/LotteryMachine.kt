package lotto.domain

class LotteryMachine {

    fun createLotteries(lotteries: Lotteries, autoNumber: Int): Lotteries =
        lotteries.plus(generateLotteries(autoNumber))

    fun generateLotteries(count: Int): Lotteries {
        val generator: LotteryGenerator = LotteryGenerator()
        return generator.generateLotteries(count)
    }
}

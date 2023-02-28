package lotto.domain

class LotteryMachine(
    private val generator: LotteryGenerator = LotteryGenerator()
) {

    fun createLotteries(lotteries: Lotteries, autoNumber: Int): Lotteries =
        lotteries.plus(generateLotteries(autoNumber))

    fun generateLotteries(count: Int): Lotteries {
        return generator.generateLotteries(count)
    }
}

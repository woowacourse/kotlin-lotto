package lotto.domain

class LotteryMachine {

    fun generateLotteries(count: Int): Lotteries {
        val generator: LotteryGenerator = LotteryGenerator()
        return generator.generateLotteries(count)
    }
}

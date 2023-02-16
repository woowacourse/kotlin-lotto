package lotto.domain

class LotteriesGenerator {
    fun generate(numberGenerator: LotteryNumberGenerator, numberOfLottery: Int): List<Lottery> {
        val lotteries = mutableListOf<Lottery>()
        repeat(numberOfLottery) {
            lotteries.add(generateLottery(numberGenerator))
        }
        return lotteries
    }

    private fun generateLottery(numberGenerator: LotteryNumberGenerator): Lottery {
        val numbers = mutableSetOf<Int>()
        while (numbers.size != LOTTERY_SIZE) {
            numbers.add(numberGenerator.generate())
        }
        return Lottery(numbers.map { LotteryNumber(it) })
    }

    companion object {
        private const val LOTTERY_SIZE = 6
    }
}

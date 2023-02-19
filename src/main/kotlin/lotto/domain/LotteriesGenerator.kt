package lotto.domain

class LotteriesGenerator {
    fun generate(numberGenerator: LotteryNumbersGenerator, numberOfLottery: Int): List<Lottery> {
        val lotteries = mutableListOf<Lottery>()
        repeat(numberOfLottery) {
            lotteries.add(generateLottery(numberGenerator))
        }
        return lotteries
    }

    private fun generateLottery(numbersGenerator: LotteryNumbersGenerator): Lottery {
        val numbers = numbersGenerator.generate()
        return Lottery(numbers.map { LotteryNumber(it) })
    }
}

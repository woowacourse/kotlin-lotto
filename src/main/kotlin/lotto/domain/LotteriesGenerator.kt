package lotto.domain

class LotteriesGenerator {
    fun generate(numberOfLottery: Int, numberGenerator: LotteryNumbersGenerator): List<Lottery> {
        val lotteries = mutableListOf<Lottery>()
        repeat(numberOfLottery) {
            lotteries.add(generateLottery(numberGenerator))
        }
        return lotteries
    }

    private fun generateLottery(numbersGenerator: LotteryNumbersGenerator): Lottery {
        val numbers = numbersGenerator.generate()
        return Lottery(numbers.map { LotteryNumber.from(it) })
    }
}

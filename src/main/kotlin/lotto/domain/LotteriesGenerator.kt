package lotto.domain

class LotteriesGenerator {
    fun generate(numberOfLottery: Int, numberGenerator: LotteryNumbersGenerator): List<Lottery> {
        return List(numberOfLottery) { generateLottery(numberGenerator) }
    }

    private fun generateLottery(numbersGenerator: LotteryNumbersGenerator): Lottery {
        val numbers = numbersGenerator.generate()
        return Lottery(numbers.map { LotteryNumber.from(it) })
    }
}

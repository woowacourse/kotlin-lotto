package lotto.domain

class LotteriesGenerator {
    fun generate(numberOfLottery: Int, lotteryGenerator: LotteryGenerator): List<Lottery> {
        return List(numberOfLottery) { generateLottery(lotteryGenerator) }
    }

    private fun generateLottery(lotteryGenerator: LotteryGenerator): Lottery {
        return lotteryGenerator.generate()
    }
}

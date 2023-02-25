package lotto.domain

class LotteriesGenerator {
    fun generate(numberOfLottery: Int, lotteryGenerator: LotteryGenerator): List<Lottery> {
        return List(numberOfLottery) { generateLottery(lotteryGenerator) }
    }

    private fun generateLottery(lotteryGenerator: LotteryGenerator): Lottery {
        return lotteryGenerator.generate()
    }

    fun generateManually(numberOfLottery: Int, lotteryGenerator: ManualLotteryGenerator): List<Lottery> {
        require(numberOfLottery == lotteryGenerator.size) { }
        return List(numberOfLottery) { generateLottery(lotteryGenerator) }
    }

    companion object {
        private const val MANUAL_LOTTERY_SIZE_ERROR_MESSAGE = "구매한 수동 로또 개수가 발급을 원하시는 수동 로또 개수와 다릅니다."
    }
}

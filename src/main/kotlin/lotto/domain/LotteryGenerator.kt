package lotto.domain

class LotteryGenerator {

    fun generateLottery(): Lottery {
        val lotteryNumbers: MutableList<LotteryNumber> = mutableListOf()
        repeat(6) {
            lotteryNumbers.add(generateUniqueLotteryNumber(lotteryNumbers))
        }

        return Lottery(lotteryNumbers)
    }

    private fun generateUniqueLotteryNumber(lotteryNumbers: List<LotteryNumber>): LotteryNumber {
        val lotteryNumberGenerator = LotteryNumberGenerator()

        while (true) {
            val randomLotteryNumber = lotteryNumberGenerator.generateLotteryNumber()

            if (lotteryNumbers.contains(randomLotteryNumber)) continue
            return randomLotteryNumber
        }
    }
}

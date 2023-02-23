package lotto.domain

class RandomLotteryGenerator : LotteryGenerator {
    private val numbers = LotteryNumber.all()

    override fun generate(): Lottery {
        return Lottery(numbers.shuffled().subList(FROM_INDEX, TO_INDEX))
    }

    companion object {
        private const val FROM_INDEX = 0
        private const val TO_INDEX = 6
    }
}

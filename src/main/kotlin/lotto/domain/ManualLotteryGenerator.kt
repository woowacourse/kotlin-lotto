package lotto.domain

class ManualLotteryGenerator(numbers: List<List<Int>>) : LotteryGenerator {
    private val numbers = numbers.toMutableList()
    val size = numbers.size

    override fun generate(): Lottery {
        return Lottery(numbers.removeAt(0).map { LotteryNumber.from(it) })
    }
}

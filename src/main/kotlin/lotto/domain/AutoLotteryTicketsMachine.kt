package lotto.domain

class AutoLotteryTicketsMachine(private val quantity: Int) : LotteryTicketsMachine {
    private val numbers = LotteryNumber.all()

    override fun generate(): List<Lottery> {
        return List(quantity) { Lottery(numbers.shuffled().take(NUMBER_SIZE)) }
    }

    companion object {
        private const val NUMBER_SIZE = 6
    }
}

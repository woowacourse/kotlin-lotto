package lotto.domain

class AutoLotteryTicketsMachine(private val quantity: Int) : LotteryTicketsMachine {
    private val numbers = LotteryNumber.all()

    override fun generate(): List<Lottery> {
        return List(quantity) { Lottery(numbers.shuffled().subList(FROM_INDEX, TO_INDEX)) }
    }

    companion object {
        private const val FROM_INDEX = 0
        private const val TO_INDEX = 6
    }
}

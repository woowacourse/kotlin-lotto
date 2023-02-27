package lotto.domain

class ManualLotteryTicketsMachine(private val numbers: List<List<Int>>) : LotteryTicketsMachine {
    override fun generate(): List<Lottery> {
        return numbers.map { Lottery.from(it) }
    }
}

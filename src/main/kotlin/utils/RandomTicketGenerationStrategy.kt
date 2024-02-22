package utils

import entity.Ticket
import model.Amount
import model.Lottery
import model.LotteryNumber

class RandomTicketGenerationStrategy(private val amount: Amount) : TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val lotteries = mutableListOf<Lottery>()
        repeat(amount.money / LOTTO_TICKET_PRICE) {
            lotteries.add(Lottery(generateNumbers()))
        }
        return Ticket(lotteries, amount)
    }

    private fun generateNumbers(): List<LotteryNumber> =
        (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
            .toList()
            .shuffled()
            .take(LOTTO_COUNT)
            .sorted()
            .map {
                LotteryNumber(it)
            }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val LOTTO_COUNT = 6
        private const val LOTTO_TICKET_PRICE = 1000
    }
}

package utils

import entity.Ticket
import model.Amount
import model.Lottery
import model.LotteryPurchasePattern

class RandomTicketGenerationStrategy(private val amount: Amount) : TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val lotteries = mutableListOf<Lottery>()
        repeat(amount.money / LOTTO_TICKET_PRICE) {
            lotteries.add(Lottery(RandomLotteryGenerator.generateNumbers()))
        }
        return Ticket(lotteries, amount, LotteryPurchasePattern(0, lotteries.size))
    }

    companion object {
        private const val LOTTO_TICKET_PRICE = 1000
    }
}

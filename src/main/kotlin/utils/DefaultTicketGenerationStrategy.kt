package utils

import entity.Ticket
import model.Amount
import model.Lottery
import model.LotteryPurchasePattern

class DefaultTicketGenerationStrategy : TicketGenerationStrategy {
    private val defaultLotteries = listOf(Lottery.fromList(listOf(1, 2, 3, 4, 5, 6)))
    private val defaultAmount = Amount(1000)

    override fun issueTicket(): Ticket {
        return Ticket(defaultLotteries, defaultAmount, LotteryPurchasePattern(0, defaultLotteries.size))
    }
}

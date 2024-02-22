package utils

import entity.Ticket
import model.Amount
import model.Lotto

class DefaultTicketGenerationStrategy : TicketGenerationStrategy {
    private val defaultLotteries = listOf(Lotto.fromList(listOf(1, 2, 3, 4, 5, 6)))
    private val defaultAmount = Amount(1000)

    override fun issueTicket(): Ticket {
        return Ticket(defaultLotteries, defaultAmount)
    }
}

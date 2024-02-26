package model

import entity.Ticket
import utils.TicketGenerationStrategy

class LotteryStore(
    private val ticketGenerationStrategy: TicketGenerationStrategy,
) {
    fun issueTicket(): Ticket {
        return ticketGenerationStrategy.issueTicket()
    }
}

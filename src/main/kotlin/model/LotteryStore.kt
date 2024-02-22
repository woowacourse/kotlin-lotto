package model

import entity.Ticket
import utils.DefaultTicketGenerationStrategy
import utils.TicketGenerationStrategy

class LotteryStore {
    private var ticketGenerationStrategy: TicketGenerationStrategy = DefaultTicketGenerationStrategy()

    fun setStrategy(ticketGenerationStrategy: TicketGenerationStrategy): LotteryStore {
        this.ticketGenerationStrategy = ticketGenerationStrategy
        return this
    }

    fun issueTicket(): Ticket {
        return ticketGenerationStrategy.issueTicket()
    }
}

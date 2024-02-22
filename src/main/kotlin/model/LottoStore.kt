package model

import entity.Ticket
import utils.DefaultTicketGenerationStrategy
import utils.TicketGenerationStrategy

class LottoStore {
    private var ticketGenerationStrategy: TicketGenerationStrategy = DefaultTicketGenerationStrategy()

    fun setStrategy(ticketGenerationStrategy: TicketGenerationStrategy): LottoStore {
        this.ticketGenerationStrategy = ticketGenerationStrategy
        return this
    }

    fun issueTicket(): Ticket {
        return ticketGenerationStrategy.issueTicket()
    }
}

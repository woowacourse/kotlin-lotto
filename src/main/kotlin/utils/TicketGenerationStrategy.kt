package utils

import entity.Ticket

interface TicketGenerationStrategy {
    fun issueTicket(): Ticket
}

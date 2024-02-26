package utils

import entity.Ticket
import model.Amount
import model.Lottery

class ExplicitTicketGenerationStrategy(
    private val amount: Amount,
    private val explicitLotteriesNumbers: List<Set<Int>>,
) : TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val explicitLotteries = explicitLotteriesNumbers.map { Lottery.fromSet(it) }
        return Ticket(explicitLotteries, amount)
    }
}

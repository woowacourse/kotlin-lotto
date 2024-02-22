package utils

import entity.Ticket
import model.Amount
import model.Lotto

class ExplicitTicketGenerationStrategy(private val amount: Amount, private val lotteries: List<List<Int>>) :
    TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val explicitLottery = lotteries.map { Lotto.fromList(it) }.toList()
        return Ticket(explicitLottery, amount)
    }
}

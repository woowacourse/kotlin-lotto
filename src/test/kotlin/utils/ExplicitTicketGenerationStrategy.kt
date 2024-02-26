package utils

import entity.Ticket
import model.Amount
import model.Lottery
import model.LotteryPurchasePattern

class ExplicitTicketGenerationStrategy(
    private val amount: Amount,
    private val explicitLotteriesNumbers: List<List<Int>>,
) :
    TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val explicitLotteries = explicitLotteriesNumbers.map { Lottery.fromList(it) }.toList()
        return Ticket(explicitLotteries, amount, LotteryPurchasePattern(explicitLotteries.size, 0))
    }
}

package utils

import entity.Ticket
import model.Amount
import model.Lottery
import model.LotteryPurchasePattern

class MixedTicketGenerationStrategy(
    private val amount: Amount,
    private val explicitLotteriesInput: List<String>,
    private val lotteryPurchasePattern: LotteryPurchasePattern,
) : TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val lotteries = mutableListOf<Lottery>()

        lotteries.addAll(generateExplicitLotteries())
        lotteries.addAll(generateRandomLotteries())

        return Ticket(lotteries, amount, lotteryPurchasePattern)
    }

    private fun generateRandomLotteries() =
        List(lotteryPurchasePattern.autoLottoCount) {
            Lottery(RandomLotteryGenerator.generateNumbers())
        }

    private fun generateExplicitLotteries(): List<Lottery> {
        return explicitLotteriesInput.map { Lottery.fromInput(it) }.toList()
    }
}

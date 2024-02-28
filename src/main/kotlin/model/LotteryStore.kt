package model

import entity.Ticket
import utils.LotteriesGenerationStrategy

object LotteryStore {
    fun issueLotteries(lotteriesGenerationStrategy: LotteriesGenerationStrategy): List<Lottery> {
        return lotteriesGenerationStrategy.issueLotteries()
    }

    fun issueTicket(
        manualLotteries: List<Lottery>,
        autoLotteries: List<Lottery>,
        amount: Amount,
    ): Ticket {
        val userLotteries = mutableListOf<Lottery>()

        userLotteries.addAll(manualLotteries)
        userLotteries.addAll(autoLotteries)
        return Ticket(userLotteries, amount)
    }
}

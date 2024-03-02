package model

import utils.LotteriesGenerationStrategy
import utils.ManualLotteriesGenerationStrategy

object LotteryMachine {
    fun issueLotteries(lotteriesGenerationStrategy: LotteriesGenerationStrategy): List<Lottery> {
        return lotteriesGenerationStrategy.issueLotteries()
    }

    fun issueTicket(
        manualLotteriesInput: List<List<String>>,
        strategyForAuto: LotteriesGenerationStrategy,
        purchaseInformation: PurchaseInformation,
    ): Ticket {
        val manualLotteries = issueLotteries(ManualLotteriesGenerationStrategy(manualLotteriesInput))
        val autoLotteries = issueLotteries(strategyForAuto)

        val userLotteries = manualLotteries + autoLotteries

        return Ticket(userLotteries, purchaseInformation)
    }
}

package lotto.model

import lotto.model.generator.LotteriesGenerationStrategy
import lotto.model.generator.ManualLotteriesGenerationStrategy

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

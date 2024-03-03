package lotto.model

import lotto.model.lottery.Lottery
import lotto.model.lottery.generator.LotteriesGenerationStrategy
import lotto.model.lottery.generator.ManualLotteriesGenerationStrategy
import lotto.model.puchaseinformation.PurchaseInformation

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

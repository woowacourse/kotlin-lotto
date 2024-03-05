package lotto.model

import lotto.model.lottery.Lottery
import lotto.model.lottery.strategy.LotteriesGenerationStrategy
import lotto.model.lottery.strategy.ManualLotteriesGenerationStrategy
import lotto.model.puchaseinformation.PurchaseInformation

object LotteryMachine {
    fun issueLotteries(lotteriesGenerationStrategy: LotteriesGenerationStrategy): List<Lottery> {
        return lotteriesGenerationStrategy.issueLotteries()
    }

    fun issueTicket(
        manualLotteriesInput: List<List<Int>>,
        strategyForAuto: LotteriesGenerationStrategy,
        purchaseInformation: PurchaseInformation,
    ): Ticket {
        val manualLotteries = issueLotteries(ManualLotteriesGenerationStrategy(manualLotteriesInput))
        val autoLotteries = issueLotteries(strategyForAuto)

        val userLotteries = manualLotteries + autoLotteries

        return Ticket(userLotteries, purchaseInformation)
    }
}

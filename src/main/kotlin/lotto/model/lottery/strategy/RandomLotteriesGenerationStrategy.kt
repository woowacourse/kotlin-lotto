package lotto.model.lottery.strategy

import lotto.model.lottery.Lottery
import lotto.model.puchaseinformation.PurchaseInformation

class RandomLotteriesGenerationStrategy(
    private val purchaseInformation: PurchaseInformation,
    private val lotteryTicketPrice: Int = 1_000,
    private val randomLotteryNumberGenerator: LotteryNumberGenerator = RandomLotteryNumberGenerator,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> {
        val totalLotteriesCount = purchaseInformation.amount / lotteryTicketPrice
        val manualLotteriesCount = purchaseInformation.manualLotteryCount

        return List(totalLotteriesCount - manualLotteriesCount) {
            Lottery(randomLotteryNumberGenerator.generateNumbers(), false)
        }
    }
}

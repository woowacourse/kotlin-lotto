package utils

import model.Lottery
import model.PurchaseInformation

class RandomLotteriesGenerationStrategy(
    private val purchaseInformation: PurchaseInformation,
    private val lotteryTicketPrice: Int = 1_000,
    private val randomLotteryNumberGenerator: LotteryNumberGenerator = RandomLotteryNumberGenerator,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> {
        val totalLotteriesCount = purchaseInformation.amount.money / lotteryTicketPrice
        val manualLotteriesCount = purchaseInformation.manualLotteryCount.count

        return List(totalLotteriesCount - manualLotteriesCount) {
            Lottery(randomLotteryNumberGenerator.generateNumbers(), false)
        }
    }
}

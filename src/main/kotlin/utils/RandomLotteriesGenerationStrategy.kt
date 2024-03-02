package utils

import model.Lottery
import model.PurchaseInformation

class RandomLotteriesGenerationStrategy(
    private val purchaseInformation: PurchaseInformation,
    private val lottoTicketPrice: Int = 1_000,
    private val randomLottoNumberGenerator: LotteryNumberGenerator = RandomLotteryNumberGenerator,
) : LotteriesGenerationStrategy {
    override fun issueLotteries(): List<Lottery> {
        val totalLotteriesCount = purchaseInformation.amount.money / lottoTicketPrice
        val manualLotteriesCount = purchaseInformation.manualLotteryCount.count

        return List(totalLotteriesCount - manualLotteriesCount) {
            Lottery(randomLottoNumberGenerator.generateNumbers(), false)
        }
    }
}

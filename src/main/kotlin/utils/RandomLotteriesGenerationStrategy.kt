package utils

import model.Amount
import model.Lottery
import model.ManualLotteryCount

class RandomLotteriesGenerationStrategy(
    private val amount: Amount,
    private val manualLotteryCount: ManualLotteryCount,
    private val lottoTicketPrice: Int = 1_000,
    private val randomLottoNumberGenerator: LotteryNumberGenerator = RandomLotteryNumberGenerator,
) : LotteriesGenerationStrategy {
    override fun issueLotteries() =
        List(amount.money / lottoTicketPrice - manualLotteryCount.count) {
            Lottery(randomLottoNumberGenerator.generateNumbers(), false)
        }
}

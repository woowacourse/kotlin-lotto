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
    override fun issueLotteries(): List<Lottery> {
        val lotteries = mutableListOf<Lottery>()

        repeat(amount.money / lottoTicketPrice - manualLotteryCount.count) {
            lotteries.add(Lottery(randomLottoNumberGenerator.generateNumbers(), false))
        }
        return lotteries
    }
}

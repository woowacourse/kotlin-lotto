package utils

import entity.Ticket
import model.Amount
import model.Lottery

class RandomTicketGenerationStrategy(
    private val amount: Amount,
    private val lottoTicketPrice: Int = 1_000,
    private val randomLottoNumberGenerator: LotteryNumberGenerator = RandomLotteryNumberGenerator,
) : TicketGenerationStrategy {
    override fun issueTicket(): Ticket {
        val lotteries = mutableListOf<Lottery>()
        repeat(amount.money / lottoTicketPrice) {
            lotteries.add(Lottery(randomLottoNumberGenerator.generateNumbers()))
        }
        return Ticket(lotteries, amount)
    }
}

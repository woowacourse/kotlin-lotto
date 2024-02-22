package utils

import model.Amount
import model.LotteryNumber
import model.Lotto

class RandomLottoGenerationStrategy(private val amount: Amount) : LottoGenerationStrategy {
    override fun generateLotteries(): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()
        repeat(amount.money / LOTTO_TICKET_PRICE) {
            lotteries.add(Lotto(generateNumbers()))
        }
        return lotteries
    }

    private fun generateNumbers(): List<LotteryNumber> =
        (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
            .toList()
            .shuffled()
            .take(LOTTO_COUNT)
            .sorted()
            .map {
                LotteryNumber(it)
            }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val LOTTO_COUNT = 6
        private const val LOTTO_TICKET_PRICE = 1000
    }
}

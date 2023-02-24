package domain

import domain.model.lotto.Lotto
import domain.model.lotto.LottoTicket

class LottoGenerator(
    private val numberOfLottos: Int = 1,
    private val numberGenerator: () -> LottoTicket = {
        (MINIMUM_NUMBER..MAXIMUM_NUMBER).drawLotteryNumbers()
    }
) {

    fun generateLottos(): List<Lotto> {
        return List(numberOfLottos) {
            val numbers = numberGenerator()
            Lotto.create(numbers)
        }
    }

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val NUMBER_COUNT = 6

        private fun IntRange.drawLotteryNumbers() = LottoTicket(this.shuffled().take(NUMBER_COUNT).sorted())
    }
}

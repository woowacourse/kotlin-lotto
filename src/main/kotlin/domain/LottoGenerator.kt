package domain

import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber

class LottoGenerator(
    private val numberOfLottos: Int,
    private val numberGenerator: () -> List<Int> = {
        (MINIMUM_NUMBER..MAXIMUM_NUMBER).drawLotteryNumbers()
    }
) {

    fun generateLottos(): List<Lotto> {
        return List(numberOfLottos) {
            val numbers = numberGenerator()
            Lotto(
                numbers.map { number ->
                    LottoNumber.from(number)
                }.toSet()
            )
        }
    }

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val NUMBER_COUNT = 6

        private fun IntRange.drawLotteryNumbers() = this.shuffled().take(NUMBER_COUNT).sorted()
    }
}

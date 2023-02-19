package domain.lottogenerator

import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber

class AutomaticLottoGenerator {

    fun generate(
        numberGenerator: () -> Set<LottoNumber> = {
            (MINIMUM_NUMBER..MAXIMUM_NUMBER).drawLottoNumbers()
        }
    ): Lotto {
        return Lotto(numberGenerator())
    }

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val ZERO = 0
        private const val NUMBER_COUNT = 6

        private fun IntRange.drawLottoNumbers() =
            this.shuffled().subList(ZERO, NUMBER_COUNT).sorted().map { number -> LottoNumber.from(number) }.toSet()
    }
}

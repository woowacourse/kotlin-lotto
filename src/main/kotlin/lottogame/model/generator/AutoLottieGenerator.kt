package lottogame.model.generator

import lottogame.model.Lotto
import lottogame.model.Money

fun interface AutoLottieGenerator {
    fun generate(purchaseCost: Money): List<Lotto>
}

class DefaultAutoLottieGenerator(
    private val price: Money = DEFAULT_LOTTO_PRICE,
    private val numbersGenerator: NumbersGenerator = RandomNumberGenerator(),
) : AutoLottieGenerator {
    override fun generate(purchaseCost: Money): List<Lotto> {
        val count = purchaseCost / price
        return List(count) {
            val lottoNums = numbersGenerator.generate(LOTTO_NUMBER_SIZE, LOTTO_NUMBER_RANGE).toIntArray()
            Lotto(*lottoNums)
        }
    }

    companion object {
        private val DEFAULT_LOTTO_PRICE = Money(1000)
        private val LOTTO_NUMBER_SIZE = 6
        private val LOTTO_NUMBER_RANGE = 1..45
    }
}

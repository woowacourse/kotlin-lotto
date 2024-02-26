package model

fun interface LottieGenerator {
    fun generate(purchaseCost: Money): List<Lotto>
}

class AutoLottieGenerator(
    private val price: Money = DEFAULT_LOTTO_PRICE,
    private val numbersGenerator: NumbersGenerator = RandomNumberGenerator(),
) : LottieGenerator {
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

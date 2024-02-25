package model

fun interface LottoGenerator {
    fun generate(purchaseCost: Money): List<Lotto>
}

class AutoLottoGenerator(
    private val price: Money = DEFAULT_LOTTO_PRICE,
    private val numbersGenerator: NumbersGenerator = RandomNumbersGenerator(),
) : LottoGenerator {
    override fun generate(purchaseCost: Money): List<Lotto> {
        val count = purchaseCost / price
        return List(count) {
            val lottoNums = numbersGenerator.generate(LOTTO_NUMBER_SIZE).toIntArray()
            Lotto(*lottoNums)
        }
    }

    companion object {
        private val DEFAULT_LOTTO_PRICE = Money(1000)
        private const val LOTTO_NUMBER_SIZE = 6
    }
}

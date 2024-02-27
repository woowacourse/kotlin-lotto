package model

fun interface LottoGenerator {
    fun generate(count: Count): List<Lotto>
}

class AutoLottoGenerator(
    private val numbersGenerator: NumbersGenerator = RandomNumbersGenerator(),
) : LottoGenerator {
    override fun generate(count: Count): List<Lotto> {
        return List(count.value) {
            val lottoNums = numbersGenerator.generate(LOTTO_NUMBER_SIZE).toIntArray()
            Lotto(*lottoNums)
        }
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
    }
}

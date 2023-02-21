package domain

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val randomNumbers: List<Int> = LOTTO_NUMBER_RANGE.shuffled().take(LOTTO_NUMBER_COUNT).sorted()
        val lottoNumbers: Set<LottoNumber> = randomNumbers.map { LottoNumber.of(it) }.toSet()
        return Lotto(lottoNumbers)
    }

    companion object {
        private const val LOTTO_NUMBER_MINIMUM = 1
        private const val LOTTO_NUMBER_MAXIMUM = 45
        private const val LOTTO_NUMBER_COUNT = 6
        private val LOTTO_NUMBER_RANGE = LOTTO_NUMBER_MINIMUM..LOTTO_NUMBER_MAXIMUM
    }
}

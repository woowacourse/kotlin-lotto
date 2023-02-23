package domain

class LottoGenerator() : LottoGeneratorInterface {
    override fun generateLotto(): Lotto {
        val possibleLottoNumbers = (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).toList()
        return Lotto(possibleLottoNumbers.map { LottoNumber(it) }.shuffled().take(LOTTO_NUMBER_COUNT))
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_COUNT = 6
    }
}

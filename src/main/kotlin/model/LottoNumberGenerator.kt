package lotto.model

class LottoNumberGenerator : NumberGenerator {
    override fun generate(): LottoNumbers =
        LottoNumbers.lottoNumbersOf(*(LOTTO_NUMBER_RANGE).shuffled().take(LOTTO_SIZE).sorted().toIntArray())

    companion object {
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        val LOTTO_NUMBER_RANGE: IntRange = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
        const val LOTTO_SIZE = 6
    }
}

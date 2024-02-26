package lotto.service

class RandomLottoNumberGenerator(
    lottoMinNumber: Int = DEFAULT_MIN_NUMBER,
    lottoMaxNumber: Int = DEFAULT_MAX_NUMBER,
) : LottoNumberGenerator {
    private val randomLottoNumbers = (lottoMinNumber..lottoMaxNumber).toList()

    override fun generate() = randomLottoNumbers.shuffled().take(LOTTO_SIZE).toSet()

    companion object {
        private const val LOTTO_SIZE = 6
        private const val DEFAULT_MIN_NUMBER = 1
        private const val DEFAULT_MAX_NUMBER = 45
    }
}

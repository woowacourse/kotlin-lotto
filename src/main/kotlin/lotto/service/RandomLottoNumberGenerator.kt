package lotto.service

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private const val LOTTO_SIZE = 6
    private const val LOTTO_MIN_NUMBER = 1
    private const val LOTTO_MAX_NUMBER = 45

    private val randomLottoNumbers = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).toList()

    override fun generate() = randomLottoNumbers.shuffled().take(LOTTO_SIZE)
}

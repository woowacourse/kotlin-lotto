package lotto.service

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private const val MIN_RANGE_NUM = 1
    private const val MAX_RANGE_NUM = 45
    private const val LOTTO_SIZE = 6

    private val randomLottoNumbers = (MIN_RANGE_NUM..MAX_RANGE_NUM).toList()

    override fun generate() =
        randomLottoNumbers.shuffled().take(LOTTO_SIZE)
}

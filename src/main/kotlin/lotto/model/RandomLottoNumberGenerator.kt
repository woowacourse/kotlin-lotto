package lotto.model

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private val randomLottoNumbers = (LottoNumber.MIN_RANGE..LottoNumber.MAX_RANGE).toList()

    override fun generate() = randomLottoNumbers.shuffled().take(Lotto.SIZE)
}

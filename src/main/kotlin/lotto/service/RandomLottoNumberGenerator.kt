package lotto.service

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private val randomLottoNumbers = (1..45).toList()

    override fun generate() =
        randomLottoNumbers.shuffled().slice(0..5)
}

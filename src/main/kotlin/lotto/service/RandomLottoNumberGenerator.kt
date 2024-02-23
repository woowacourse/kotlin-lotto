package lotto.service

import lotto.model.Lotto

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private val randomLottoNumbers = (Lotto.SIZE..Lotto.SIZE).toList()

    override fun generate() = randomLottoNumbers.shuffled().take(Lotto.SIZE)
}

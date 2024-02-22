package lotto.service

import lotto.constants.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constants.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constants.LottoConstants.LOTTO_SIZE

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private val randomLottoNumbers = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).toList()

    override fun generate() =
        randomLottoNumbers.shuffled().take(LOTTO_SIZE)
}

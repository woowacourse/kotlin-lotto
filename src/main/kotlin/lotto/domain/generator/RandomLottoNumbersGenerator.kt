package lotto.domain.generator

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): List<LottoNumber> {
        val selectedNumbers = selectLottoNumbers()
        return selectedNumbers.map { LottoNumber.from(it) }
    }

    private fun selectLottoNumbers(): List<Int> {
        val shuffledNumbers = (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER).shuffled()
        return shuffledNumbers.take(Lotto.LOTTO_NUMBER_SIZE)
    }
}

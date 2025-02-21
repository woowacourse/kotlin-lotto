package lotto.domain.service

import lotto.INVALID_LOTTO_MAX_NUMBER
import lotto.INVALID_LOTTO_MIN_NUMBER
import lotto.LOTTO_NUMBER_SIZE
import lotto.domain.model.LottoNumber

class RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): Set<LottoNumber> {
        return selectLottoNumbers().sorted().map { LottoNumber(it) }.toSet()
    }

    private fun selectLottoNumbers(): List<Int> {
        return (INVALID_LOTTO_MIN_NUMBER..INVALID_LOTTO_MAX_NUMBER).shuffled().take(LOTTO_NUMBER_SIZE)
    }
}

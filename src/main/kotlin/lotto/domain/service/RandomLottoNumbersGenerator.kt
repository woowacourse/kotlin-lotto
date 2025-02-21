package lotto.domain.service

import lotto.domain.LottoRules
import lotto.domain.model.LottoNumber

class RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): Set<LottoNumber> {
        return selectLottoNumbers().sorted().map { LottoNumber(it) }.toSet()
    }

    private fun selectLottoNumbers(): List<Int> {
        return (LottoRules.INVALID_LOTTO_MIN_NUMBER.value..LottoRules.INVALID_LOTTO_MAX_NUMBER.value).shuffled().take(
            LottoRules.LOTTO_NUMBER_SIZE.value,
        )
    }
}

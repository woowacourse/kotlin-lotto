package lotto.domain.service

import lotto.domain.LottoRules
import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class RandomLottoGenerator : LottoGenerator {
    private val lottoNumbers =
        (LottoRules.INVALID_LOTTO_MIN_NUMBER.value..LottoRules.INVALID_LOTTO_MAX_NUMBER.value).map { LottoNumber(it) }

    override fun generate(): Lotto {
        val lotto = getLottoNumbers().sorted()
        return Lotto(lotto.toSet())
    }

    private fun getLottoNumbers(): List<LottoNumber> {
        val randomLottoNumbers = lottoNumbers.shuffled()
        return randomLottoNumbers.take(LottoRules.LOTTO_NUMBER_SIZE.value)
    }
}

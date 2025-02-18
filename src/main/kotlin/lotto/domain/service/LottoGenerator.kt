package lotto.domain.service

import lotto.INVALID_LOTTO_MAX_NUMBER
import lotto.INVALID_LOTTO_MIN_NUMBER
import lotto.LOTTO_NUMBER_SIZE
import lotto.LOTTO_PRICE
import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class LottoGenerator {
    fun generate(amount: Int): List<Lotto> {
        val count = amount / LOTTO_PRICE
        return List(count) { Lotto(generateLottoNumbers()) }
    }

    private fun generateLottoNumbers(): List<LottoNumber> {
        return (INVALID_LOTTO_MIN_NUMBER..INVALID_LOTTO_MAX_NUMBER).shuffled().take(LOTTO_NUMBER_SIZE).map { LottoNumber(it) }
    }
}

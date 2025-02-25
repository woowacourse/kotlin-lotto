package lotto.generator

import lotto.domain.Lotto
import lotto.domain.Lotto.Companion.MAX_LOTTO_SIZE
import lotto.domain.LottoNumber

class AutoLottoGenerator(private val size: Int) : LottoGenerator {
    override fun generateLotto(): List<Lotto> {
        return List(size) { Lotto(getRandomLottoNumber()) }
    }

    private fun getRandomLottoNumber(): List<LottoNumber> {
        return (LottoNumber.MIN_BOUND..LottoNumber.MAX_BOUND).shuffled().take(MAX_LOTTO_SIZE).map { LottoNumber(it) }
    }
}

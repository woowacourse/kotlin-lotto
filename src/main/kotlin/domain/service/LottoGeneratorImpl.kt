package domain.service

import domain.model.Lotto
import domain.model.Lotto.Companion.LOTTO_SIZE
import domain.model.LottoNumber
import domain.model.LottoNumber.Companion.LOTTO_MAX
import domain.model.LottoNumber.Companion.LOTTO_MIN

class LottoGeneratorImpl() : LottoGenerator {
    override fun generate(quickPickLottoAmout: Int): List<Lotto> {
        return List<Lotto>(quickPickLottoAmout) { makeOneLotto() }
    }

    private fun makeOneLotto(): Lotto {
        val newLotto = (LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) }
        return Lotto(newLotto)
    }
}

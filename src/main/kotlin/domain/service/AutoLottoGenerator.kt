package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class AutoLottoGenerator : LottoGenerator {
    private fun makeRandomNumbers(): Set<Int> =
        (LOTTO_MIN..LOTTO_MAX)
            .shuffled()
            .take(6)
            .toSet()

    override fun getLottoNumbers(lottoNumbers: Set<Int>): Set<LottoNumber> = lottoNumbers.map { LottoNumber.from(it) }.toSet()

    override fun makeLotto(amount: Int): List<Lotto> = List(amount) { Lotto(getLottoNumbers(makeRandomNumbers())) }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}

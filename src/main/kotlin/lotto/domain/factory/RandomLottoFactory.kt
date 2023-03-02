package lotto.domain.factory

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class RandomLottoFactory : LottoFactory {
    override fun createLotto(): Lotto = Lotto(getRandomNumbers().map { LottoNumber(it) }.toSet())

    private fun getRandomNumbers(): List<Int> =
        (LottoNumber.LOTTO_MINIMUM_NUMBER..LottoNumber.LOTTO_MAXIMUM_NUMBER).shuffled().take(Lotto.LOTTO_SIZE)
}

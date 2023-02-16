package lotto.domain.factory

import lotto.constant.LOTTO_MAXIMUM_NUMBER
import lotto.constant.LOTTO_MINIMUM_NUMBER
import lotto.constant.LOTTO_SIZE
import lotto.domain.Lotto
import lotto.domain.LottoNumber

class RandomLottoFactory : LottoFactory {
    override fun createLotto(): Lotto = Lotto(getRandomNumbers().map { LottoNumber(it) })

    private fun getRandomNumbers(): List<Int> = (LOTTO_MINIMUM_NUMBER..LOTTO_MAXIMUM_NUMBER).shuffled().take(LOTTO_SIZE)
}

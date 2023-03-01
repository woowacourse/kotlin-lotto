package lotto.domain.factory

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class ManualLottoFactory : LottoFactory {
    override fun createLotto(numbers: List<Int>): Lotto = getManualPurchaseLotto(numbers)

    private fun getManualPurchaseLotto(numbers: List<Int>): Lotto {
        return runCatching {
            Lotto(numbers.map { LottoNumber(it) }.toSet())
        }.getOrElse { error ->
            println(error.message)
            getManualPurchaseLotto(numbers)
        }
    }
}

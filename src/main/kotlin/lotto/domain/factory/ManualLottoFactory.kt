package lotto.domain.factory

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class ManualLottoFactory : LottoFactory {
    override fun createLotto(getManualPurchaseNumbers: () -> List<Int>): Lotto = getManualPurchaseLotto(getManualPurchaseNumbers)

    private fun getManualPurchaseLotto(getManualPurchaseNumbers: () -> List<Int>): Lotto {
        return runCatching {
            Lotto(getManualPurchaseNumbers().map { LottoNumber(it) }.toSet())
        }.getOrElse { error ->
            println(error.message)
            getManualPurchaseLotto(getManualPurchaseNumbers)
        }
    }
}

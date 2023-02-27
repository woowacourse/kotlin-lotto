package lotto.domain.factory

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.view.InputView

class ManualLottoFactory : LottoFactory {
    override fun createLotto(): Lotto = getManualPurchaseLotto()

    private fun getManualPurchaseLotto(): Lotto {
        return runCatching {
            Lotto(InputView.getManualPurchaseLotto().map { LottoNumber(it) }.toSet())
        }.getOrElse { error ->
            println(error.message)
            getManualPurchaseLotto()
        }
    }
}

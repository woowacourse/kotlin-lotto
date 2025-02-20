package lotto.domain.model

import lotto.domain.service.LottoGenerator
import lotto.domain.value.PurchaseAmount

class LottoMachine {
    private val lottoGenerator = LottoGenerator()

    fun generateLottos(purchaseAmount: PurchaseAmount): PurchaseDetail {
        val lottos = List(purchaseAmount.getPurchaseQuantity()) { lottoGenerator.generateLotto() }
        return PurchaseDetail(purchaseAmount, lottos)
    }
}

package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.PurchaseAmount

class LottoMachine {
    fun generate(purchaseAmount: PurchaseAmount): List<Lotto> {
        val quantity = purchaseAmount.getPurchaseQuantity()
        return List(quantity) { Lotto.create() }
    }
}

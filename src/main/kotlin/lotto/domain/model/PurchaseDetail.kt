package lotto.domain.model

import lotto.domain.value.PurchaseAmount

class PurchaseDetail(
    val purchaseAmount: PurchaseAmount,
    val lottos: List<Lotto>,
) {
    init {
        require(lottos.size == purchaseAmount.getPurchaseQuantity())
    }
}

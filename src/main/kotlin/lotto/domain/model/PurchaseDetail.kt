package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.domain.value.PurchaseAmount

class PurchaseDetail(
    val purchaseAmount: PurchaseAmount,
    val lottos: List<Lotto>,
) {
    init {
        require(lottos.size == purchaseAmount.getPurchaseQuantity()) { ErrorMessages.INVALID_PURCHASE_DETAIL }
    }
}

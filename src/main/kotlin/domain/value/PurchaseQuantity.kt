package domain.value

import constants.LottoConstants

class PurchaseQuantity(
    purchaseAmount: PurchaseAmount,
) {
    val quantity = purchaseAmount.amount / LottoConstants.LOTTO_PRICE

    init {
        require(purchaseAmount.amount % LottoConstants.LOTTO_PRICE == 0)
        require(purchaseAmount.amount >= LottoConstants.LOTTO_PRICE)
    }
}

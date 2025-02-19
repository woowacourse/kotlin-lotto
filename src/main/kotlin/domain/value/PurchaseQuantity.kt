package domain.value

import constants.LottoConstants

class PurchaseQuantity(
    purchaseAmount: PurchaseAmount,
) {
    init {
        require(purchaseAmount.amount % LottoConstants.LOTTO_PRICE == 0)
        require(purchaseAmount.amount >= LottoConstants.LOTTO_PRICE)
    }
}

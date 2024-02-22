package lotto.model

import lotto.constants.StringConstants.INVALID_PURCHASE_PRICE

class PurchaseInfo(purchasePrice: String) {

    val price: Int
    val amount: Int

    init {
        require(purchasePrice.isValidDigit() && purchasePrice.isMoreThanMin() && purchasePrice.divideByLottoPrice()) {
            INVALID_PURCHASE_PRICE
        }
        price = purchasePrice.toInt()
        amount = price / 1_000
    }

    private fun String.isValidDigit() = toIntOrNull() != null

    private fun String.isMoreThanMin() = toInt() >= 1000

    private fun String.divideByLottoPrice() = toInt() % 1000 == 0
}

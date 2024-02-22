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
        amount = price / LOTTO_PRICE
    }

    private fun String.isValidDigit() = toIntOrNull() != null

    private fun String.isMoreThanMin() = toInt() >= LOTTO_PRICE

    private fun String.divideByLottoPrice() = toInt() % LOTTO_PRICE == 0

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

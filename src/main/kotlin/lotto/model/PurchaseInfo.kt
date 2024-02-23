package lotto.model

import lotto.constants.StringConstants.INVALID_PURCHASE_PRICE

class PurchaseInfo(val price: Int) {
    val amount: Int

    init {
        require(price.isMoreThanMin() && price.divideByLottoPrice()) { INVALID_PURCHASE_PRICE }
        amount = price / Lotto.PRICE
    }

    private fun Int.isMoreThanMin() = this >= Lotto.PRICE

    private fun Int.divideByLottoPrice() = this % Lotto.PRICE == 0
}

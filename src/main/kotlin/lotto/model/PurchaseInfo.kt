package lotto.model

import lotto.constants.LottoConstants.LOTTO_PRICE
import lotto.constants.StringConstants.INVALID_PURCHASE_PRICE

class PurchaseInfo(val price: Int) {
    val amount: Int

    init {
        require(price.isMoreThanMin() && price.divideByLottoPrice()) { INVALID_PURCHASE_PRICE }
        amount = price / LOTTO_PRICE
    }

    private fun Int.isMoreThanMin() = this >= LOTTO_PRICE

    private fun Int.divideByLottoPrice() = this % LOTTO_PRICE == 0
}

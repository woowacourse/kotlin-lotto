package lotto.domain.value

import lotto.constants.ErrorMessages
import lotto.constants.LottoConstants

@JvmInline
value class PurchaseAmount(
    val amount: Int,
) {
    init {
        require(amount >= LottoConstants.LOTTO_PRICE) { ErrorMessages.INVALID_PURCHASE_AMOUNT_RANGE }
        require(amount % LottoConstants.LOTTO_PRICE == 0) { ErrorMessages.INVALID_PURCHASE_AMOUNT }
    }

    fun getPurchaseQuantity(): LottoCount = LottoCount(amount / LottoConstants.LOTTO_PRICE)
}

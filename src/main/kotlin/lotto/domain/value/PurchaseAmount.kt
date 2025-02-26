package lotto.domain.value

import lotto.constants.ErrorMessages

@JvmInline
value class PurchaseAmount(
    val amount: Int,
) {
    init {
        require(amount >= LOTTO_PRICE) { ErrorMessages.INVALID_PURCHASE_AMOUNT_RANGE }
        require(amount % LOTTO_PRICE == 0) { ErrorMessages.INVALID_PURCHASE_AMOUNT }
    }

    fun getPurchaseQuantity(): LottoCount = LottoCount(amount / LOTTO_PRICE)

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

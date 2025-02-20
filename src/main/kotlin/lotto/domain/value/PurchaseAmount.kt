package lotto.domain.value

import lotto.constants.LottoConstants

@JvmInline
value class PurchaseAmount(
    val amount: Int,
) {
    init {
        require(amount >= LottoConstants.LOTTO_PRICE)
        require(amount % LottoConstants.LOTTO_PRICE == 0)
    }

    fun getPurchaseQuantity(): Int = amount / LottoConstants.LOTTO_PRICE
}

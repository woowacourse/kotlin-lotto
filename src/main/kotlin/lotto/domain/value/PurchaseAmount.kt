package lotto.domain.value

@JvmInline
value class PurchaseAmount(
    val amount: Int,
) {
    init {
        require(amount >= LOTTO_PRICE) { ERROR_PURCHASE_AMOUNT_RANGE }
        require(amount % LOTTO_PRICE == 0) { ERROR_PURCHASE_AMOUNT }
    }

    fun getPurchaseQuantity(): LottoCount = LottoCount(amount / LOTTO_PRICE)

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val ERROR_PURCHASE_AMOUNT_RANGE = "[ERROR] 구입 금액은 1,000원 이상이어야 합니다."
        private const val ERROR_PURCHASE_AMOUNT = "[ERROR] 구입 금액은 1,000원 단위여야 합니다."
    }
}

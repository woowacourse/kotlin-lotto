package lotto.domain.model

data class PurchaseAmount private constructor(private val amount: Int) {
    fun getRemainPurchaseCount(lottoPrice: Int = Lotto.LOTTO_PRICE) = amount / lottoPrice

    fun purchaseLotto(
        purchaseCount: Int,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): PurchaseAmount {
        val purchaseAmount = lottoPrice * purchaseCount
        require(purchaseAmount <= amount) { INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE.format(purchaseAmount, amount) }
        return this.copy(amount = amount - purchaseAmount)
    }

    companion object {
        fun from(amount: Int): PurchaseAmount {
            require(amount >= Lotto.LOTTO_PRICE) { INVALID_PURCHASE_MIN_LOTTO_PRICE.format(amount) }
            return PurchaseAmount(amount)
        }

        private const val INVALID_PURCHASE_MIN_LOTTO_PRICE = "%s원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다."
        private const val INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE =
            "선택하신 개수의 금액은 %d 입니다. 구매하시는 금액은 현재 구매할 %d원 보다 작거나 같아야 합니다."
    }
}

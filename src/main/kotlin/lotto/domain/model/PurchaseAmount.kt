package lotto.domain.model

class PurchaseAmount(private var _amount: Int) {
    val amount get() = _amount

    init {
        require(amount >= Lotto.LOTTO_PRICE) { INVALID_MIN_AMOUNT_MESSAGE.format(amount) }
    }

    fun purchaseLotto(
        purchaseCount: Int,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): Int {
        val purchaseAmount = lottoPrice * purchaseCount
        require(purchaseAmount <= amount) { INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE.format(purchaseAmount, amount) }
        _amount -= purchaseAmount
        return purchaseCount
    }

    private companion object {
        const val INVALID_MIN_AMOUNT_MESSAGE = "%d원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다."
        const val INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE = "선택하신 개수의 금액은 %d 입니다. 구매하시는 금액은 현재 구매할 %d원 보다 적어야 합니다."
    }
}

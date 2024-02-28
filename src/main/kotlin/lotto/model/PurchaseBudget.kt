package lotto.model

class PurchaseBudget(
    val amount: Int,
    val pricePerManualLotto: Int = Lotto.PRICE_PER_LOTTO,
    val pricePerAutoLotto: Int = Lotto.PRICE_PER_LOTTO,
) {
    init {
        require(amount >= pricePerManualLotto || amount >= pricePerAutoLotto) { PRICE_ERROR_MESSAGE }
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 로또 1장의 가격 이상이여야합니다."
    }
}

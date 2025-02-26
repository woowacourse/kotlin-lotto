package lotto.domain.model

class PurchaseAmount(amount: Int) {
    var amount: Int = amount
        private set

    init {
        require(amount >= MIN_PURCHASE_VALUE) { INVALID_PURCHASE_VALUE_MESSAGE }
    }

    fun calculatePurchaseLottoCount(lottoPrice: Int): Int {
        return amount / lottoPrice
    }

    fun reducePurchaseAmount(reduceAmount: Int) {
        require(this.amount >= reduceAmount) { INVALID_REDUCE_AMOUNT_MESSAGE }
        this.amount -= reduceAmount
    }

    companion object {
        private const val MIN_PURCHASE_VALUE: Int = 0
        private const val INVALID_PURCHASE_VALUE_MESSAGE: String = "구매 금액은 양수여야 합니다."
        private const val INVALID_REDUCE_AMOUNT_MESSAGE: String = "금액이 부족합니다."
    }
}

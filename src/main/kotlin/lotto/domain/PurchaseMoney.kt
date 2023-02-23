package lotto.domain

class PurchaseMoney(val value: Int) {
    init {
        validatePurchaseMoneyRange()
    }

    fun getPurchaseCount(unit: Int) = value / unit

    private fun validatePurchaseMoneyRange() {
        require(value in MINIMUM_PURCHASE_MONEY..MAXIMUM_PURCHASE_MONEY) { PURCHASE_MONEY_RANGE_ERROR }
    }

    companion object {
        private const val MINIMUM_PURCHASE_MONEY = 1000
        private const val MAXIMUM_PURCHASE_MONEY = 100_000
        private const val PURCHASE_MONEY_RANGE_ERROR = "구입 금액은 천원 이상 10만원 이하여야 합니다."
    }
}

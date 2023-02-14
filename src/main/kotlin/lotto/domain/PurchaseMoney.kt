package lotto.domain

class PurchaseMoney(val value: Int) {
    init {
        validatePurchaseMoneyUnit()
        validatePurchaseMoneyRange()
    }

    private fun validatePurchaseMoneyUnit() {
        require(value % PURCHASE_UNIT == 0) { PURCHASE_MONEY_UNIT_ERROR }
    }

    private fun validatePurchaseMoneyRange() {
        require(value >= 0) { PURCHASE_MONEY_RANGE_ERROR }
    }

    companion object {
        private const val PURCHASE_UNIT = 1000
        private const val PURCHASE_MONEY_UNIT_ERROR = "구입 금액은 천원 단위여야 합니다."
        private const val PURCHASE_MONEY_RANGE_ERROR = "구입 금액은 0 이상의 수여야 합니다."
    }
}

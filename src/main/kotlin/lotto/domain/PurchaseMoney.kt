package lotto.domain

class PurchaseMoney(val purchaseMoney: Int) {
    init {
        validatePurchaseMoneyUnit()
    }

    private fun validatePurchaseMoneyUnit() {
        require(purchaseMoney % PURCHASE_UNIT == 0) { PURCHASE_MONEY_UNIT_ERROR }
    }

    companion object {
        private const val PURCHASE_UNIT = 1000
        private const val PURCHASE_MONEY_UNIT_ERROR = "구입 금액은 천원 단위여야 합니다."
    }
}

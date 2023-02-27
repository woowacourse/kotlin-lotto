package lotto.domain.purchasecount

class TotalPurchaseCount(totalPurchaseCount: Int, manualPurchaseCount: Int) {
    val autoPurchaseCount: PurchaseCount
    val manualPurchaseCount: PurchaseCount

    init {
        checkValidateManualPurchaseCount(totalPurchaseCount, manualPurchaseCount)
        autoPurchaseCount = PurchaseCount(totalPurchaseCount - manualPurchaseCount)
        this.manualPurchaseCount = PurchaseCount(manualPurchaseCount)
    }

    private fun checkValidateManualPurchaseCount(totalPurchaseCount: Int, manualPurchaseCount: Int) {
        require(manualPurchaseCount < totalPurchaseCount) { BIGGER_MANUAL_PURCHASE_MONEY_ERROR }
    }

    companion object {
        private const val BIGGER_MANUAL_PURCHASE_MONEY_ERROR = "수동 구입 로또수는 총 구입로또 수 보다 작아야 합니다."
    }
}

package lotto.domain

@JvmInline
value class ManualPurchaseCount private constructor(val value: Int) {

    companion object {
        private const val BIGGER_MANUAL_PURCHASE_MONEY = "수동 구입 로또수는 총 구입로또 수 보다 작아야 합니다."

        fun ManualPurchaseCount(manualPurchaseCount: Int, purchaseCount: Int): ManualPurchaseCount {
            require(manualPurchaseCount < purchaseCount) { BIGGER_MANUAL_PURCHASE_MONEY }
            return ManualPurchaseCount(manualPurchaseCount)
        }
    }
}

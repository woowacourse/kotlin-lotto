package lotto.domain

@JvmInline
value class ManualPurchaseCount private constructor(val value: Int) {

    init {
        require(value > 0) { NEGATIVE_MANUAL_PURCHASE_MONEY_ERROR }
    }

    companion object {
        private const val BIGGER_MANUAL_PURCHASE_MONEY_ERROR = "수동 구입 로또수는 총 구입로또 수 보다 작아야 합니다."
        private const val NEGATIVE_MANUAL_PURCHASE_MONEY_ERROR = "수동 구입 로또수는 음수일 수 없습니다"

        fun from(manualPurchaseCount: Int, purchaseCount: Int): ManualPurchaseCount {
            require(manualPurchaseCount < purchaseCount) { BIGGER_MANUAL_PURCHASE_MONEY_ERROR }
            return ManualPurchaseCount(manualPurchaseCount)
        }
    }
}

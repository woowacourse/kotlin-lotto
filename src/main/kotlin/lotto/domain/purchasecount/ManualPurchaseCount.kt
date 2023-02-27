package lotto.domain.purchasecount

@JvmInline
value class ManualPurchaseCount(val value: Int) {

    init {
        require(value > 0) { NEGATIVE_MANUAL_PURCHASE_MONEY_ERROR }
    }

    companion object {
        private const val NEGATIVE_MANUAL_PURCHASE_MONEY_ERROR = "수동 구입 로또수는 음수일 수 없습니다"
    }
}

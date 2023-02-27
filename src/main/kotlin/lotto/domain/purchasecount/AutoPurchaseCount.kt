package lotto.domain.purchasecount

@JvmInline
value class AutoPurchaseCount(val value: Int) {

    init {
        require(value > 0) { NEGATIVE_AUTO_PURCHASE_MONEY_ERROR }
    }

    companion object {
        private const val NEGATIVE_AUTO_PURCHASE_MONEY_ERROR = "수동 구입 로또수는 음수일 수 없습니다"
    }
}

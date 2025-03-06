package domain.model

import ERROR

@JvmInline
value class PurchasePrice(val value: Int) {
    init {
        require(value > 0) { INVALID_MINIMUM_PURCHASE_AMOUNT }
    }

    companion object {
        const val INVALID_MINIMUM_PURCHASE_AMOUNT = "$ERROR 천원 이상 입력해주세요."
        const val STANDARD_AMOUNT_UNIT = 1000
    }
}

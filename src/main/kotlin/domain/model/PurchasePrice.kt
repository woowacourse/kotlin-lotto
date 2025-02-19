package lotto.domain.model

import lotto.Lotto.Companion.ERROR

@JvmInline
value class PurchasePrice(val value: Int) {
    init {
        require(value > 0) { INVALID_MINIMUM_PURCHASE_AMOUNT }
        require(value % STANDARD_AMOUNT_UNIT == 0) { INVALID_THOUSAND_WON_UNIT }
    }

    companion object {
        const val INVALID_MINIMUM_PURCHASE_AMOUNT = "$ERROR 천원 이상 입력해주세요."
        const val INVALID_THOUSAND_WON_UNIT = "$ERROR 천원 단위로 입력해주세요."
        const val STANDARD_AMOUNT_UNIT = 1000
    }
}

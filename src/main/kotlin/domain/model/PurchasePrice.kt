package domain.model

import domain.model.Lotto.Companion.ERROR

class PurchasePrice(
    val value: Int,
    private val unit: Int = DEFAULT_AMOUNT_UNIT,
) {
    init {
        require(value > 0) { INVALID_MINIMUM_PURCHASE_AMOUNT }
        require(value % DEFAULT_AMOUNT_UNIT == 0) { INVALID_THOUSAND_WON_UNIT }
    }

    companion object {
        fun PurchasePrice.toAmount(): Int = value / unit

        const val DEFAULT_AMOUNT_UNIT = 1000
        const val INVALID_MINIMUM_PURCHASE_AMOUNT = "$ERROR ${DEFAULT_AMOUNT_UNIT}원 이상 입력해주세요."
        const val INVALID_THOUSAND_WON_UNIT = "$ERROR ${DEFAULT_AMOUNT_UNIT}원 단위로 입력해주세요."
    }
}

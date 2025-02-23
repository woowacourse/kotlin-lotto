package domain.model

import domain.service.LottoGenerator.Companion.STANDARD_AMOUNT_UNIT

@JvmInline
value class PurchasePrice(val value: Int) {
    fun getPurchasableLottoCount(standardPrice: Int = STANDARD_AMOUNT_UNIT): Int {
        return value / standardPrice
    }
}

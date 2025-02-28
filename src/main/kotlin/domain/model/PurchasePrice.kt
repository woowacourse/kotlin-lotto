package domain.model

import domain.model.Lotto.Companion.ERROR

sealed class PurchasePriceResult {
    data class Success(
        val purchasePrice: PurchasePrice,
    ) : PurchasePriceResult()

    data class InvalidMinimumPurchaseAmount(
        val errorMsg: String,
    ) : PurchasePriceResult()

    data class InvalidThousandWonUnit(
        val errorMsg: String,
    ) : PurchasePriceResult()
}

class PurchasePrice private constructor(
    val value: Int,
    private val unit: Int = DEFAULT_AMOUNT_UNIT,
) {
    companion object {
        fun Int.toPurchasePriceResult(unit: Int = DEFAULT_AMOUNT_UNIT): PurchasePriceResult {
            if (this <= 0) return PurchasePriceResult.InvalidMinimumPurchaseAmount(INVALID_MINIMUM_PURCHASE_AMOUNT)
            if (this % unit != 0) return PurchasePriceResult.InvalidThousandWonUnit(INVALID_THOUSAND_WON_UNIT)
            return PurchasePriceResult.Success(PurchasePrice(this))
        }

        fun PurchasePrice.toAmount(): Int = value / unit

        const val DEFAULT_AMOUNT_UNIT = 1000
        private const val INVALID_MINIMUM_PURCHASE_AMOUNT = "$ERROR ${DEFAULT_AMOUNT_UNIT}원 이상 입력해주세요."
        private const val INVALID_THOUSAND_WON_UNIT = "$ERROR ${DEFAULT_AMOUNT_UNIT}원 단위로 입력해주세요."
    }
}

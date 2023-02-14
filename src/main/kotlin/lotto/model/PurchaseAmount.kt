package lotto.model

class PurchaseAmount(
    val amount: Int
) {
    init {
        require(amount in PURCHASE_AMOUNT_LOWER_BOUNDARY..PURCHASE_AMOUNT_UPPER_BOUNDARY) {
            PURCHASE_AMOUNT_BOUNDARY_ERROR_MESSAGE
        }
    }

    companion object {
        private const val PURCHASE_AMOUNT_BOUNDARY_ERROR_MESSAGE = "구입금액은 1000원 이상 5만원 이하여야 합니다."
        private const val PURCHASE_AMOUNT_LOWER_BOUNDARY = 1000
        private const val PURCHASE_AMOUNT_UPPER_BOUNDARY = 50000
    }
}

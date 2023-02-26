package lotto.domain

class PurchaseAmount(
    val amount: Int
) {
    val count = amount / PURCHASE_AMOUNT_UNIT

    init {
        checkBoundary()
        checkUnit()
    }

    private fun checkBoundary() {
        require(amount in PURCHASE_AMOUNT_LOWER_BOUNDARY..PURCHASE_AMOUNT_UPPER_BOUNDARY) {
            "$PURCHASE_AMOUNT_BOUNDARY_ERROR_MESSAGE\n" +
                "오류값 : $amount"
        }
    }

    private fun checkUnit() {
        require(amount % PURCHASE_AMOUNT_UNIT == 0) {
            "$PURCHASE_AMOUNT_UNIT_ERROR_MESSAGE\n" +
                "오류값 : $amount"
        }
    }

    fun getAutoPurchaseCount(quantity: Int): Int {
        return count - quantity
    }

    companion object {
        private const val PURCHASE_AMOUNT_BOUNDARY_ERROR_MESSAGE = "구입금액은 1000원 이상 5만원 이하여야 합니다."
        private const val PURCHASE_AMOUNT_LOWER_BOUNDARY = 1000
        private const val PURCHASE_AMOUNT_UNIT = 1000
        private const val PURCHASE_AMOUNT_UNIT_ERROR_MESSAGE = "구입금액은 1000원 단위여야 합니다."
        private const val PURCHASE_AMOUNT_UPPER_BOUNDARY = 50000
    }
}

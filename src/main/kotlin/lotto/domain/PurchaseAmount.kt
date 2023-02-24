package lotto.domain

class PurchaseAmount(
    val amount: Int
) {
    init {
        checkBoundary()
        checkUnit()
    }

    private fun checkBoundary() {
        require(amount in PURCHASE_AMOUNT_LOWER_BOUNDARY..PURCHASE_AMOUNT_UPPER_BOUNDARY) {
            PURCHASE_AMOUNT_BOUNDARY_ERROR_MESSAGE
        }
    }

    private fun checkUnit() {
        require(amount % PURCHASE_AMOUNT_UNIT == 0) { PURCHASE_AMOUNT_UNIT_ERROR_MESSAGE }
    }

    fun getPurchaseQuantity(): Int = amount / PURCHASE_AMOUNT_UNIT

    fun checkEnough(quantity: Int) {
        require(quantity <= getPurchaseQuantity()) { MANUAL_QUANTITY_ERROR_MESSAGE }
    }

    companion object {
        private const val MANUAL_QUANTITY_ERROR_MESSAGE = "구입금액보다 수동 로또 개수가 더 많습니다."
        private const val PURCHASE_AMOUNT_BOUNDARY_ERROR_MESSAGE = "구입금액은 1000원 이상 5만원 이하여야 합니다."
        private const val PURCHASE_AMOUNT_LOWER_BOUNDARY = 1000
        private const val PURCHASE_AMOUNT_UNIT = 1000
        private const val PURCHASE_AMOUNT_UNIT_ERROR_MESSAGE = "구입금액은 1000원 단위여야 합니다."
        private const val PURCHASE_AMOUNT_UPPER_BOUNDARY = 50000
    }
}

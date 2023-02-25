package lotto.domain

class PurchaseAmount(
    private val amount: Int,
    val autoNumber: Int
) {

    val manualNumber: Int = getPurchaseQuantity() - autoNumber

    init {
        checkBoundary()
        checkUnit()
        checkAutoNumber()
    }

    fun toInt(): Int = amount

    private fun getPurchaseQuantity(): Int = amount / PURCHASE_AMOUNT_UNIT

    private fun checkBoundary() {
        require(amount in PURCHASE_AMOUNT_LOWER_BOUNDARY..PURCHASE_AMOUNT_UPPER_BOUNDARY) {
            ERROR_MESSAGE_PURCHASE_AMOUNT_BOUNDARY
        }
    }

    private fun checkUnit() {
        require(amount % PURCHASE_AMOUNT_UNIT == 0) { ERROR_MESSAGE_PURCHASE_AMOUNT_UNIT }
    }

    private fun checkAutoNumber() {
        val purchaseQuantity = getPurchaseQuantity()
        require(purchaseQuantity >= autoNumber) { ERROR_FORMAT_MESSAGE_AUTO_NUMBER.format(purchaseQuantity) }
    }

    companion object {
        private const val ERROR_MESSAGE_PURCHASE_AMOUNT_BOUNDARY = "구입금액은 1000원 이상 5만원 이하여야 합니다."
        private const val ERROR_MESSAGE_PURCHASE_AMOUNT_UNIT = "구입금액은 1000원 단위여야 합니다."
        private const val ERROR_FORMAT_MESSAGE_AUTO_NUMBER = "수동 구매 로또의 수는 %d개를 넘을 수 없습니다."

        private const val PURCHASE_AMOUNT_LOWER_BOUNDARY = 1000
        private const val PURCHASE_AMOUNT_UNIT = 1000
        private const val PURCHASE_AMOUNT_UPPER_BOUNDARY = 50000
    }
}

package domain.value

class PurchaseAmount(
    amount: Int,
) {
    init {
        require(amount >= MINIMUM_PURCHASE_AMOUNT)
    }

    companion object {
        private const val MINIMUM_PURCHASE_AMOUNT = 0
    }
}

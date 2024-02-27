package lotto.model

class PurchaseDetails(
    private val purchaseBudget: PurchaseBudget,
    val manualPurchaseCount: Int,
    autoPurchaseCount: Int? = null,
) {
    val autoPurchaseCount: Int = autoPurchaseCount ?: (calculateRemainingBudgetAfterManual() / purchaseBudget.pricePerAutoLotto)

    init {
        require(purchaseBudget.amount >= calculateTotalPurchaseCost())
    }

    fun calculateTotalPurchaseCost() = calculateManualPurchaseCost() + calculateAutoPurchaseCost()

    private fun calculateManualPurchaseCost() = manualPurchaseCount * purchaseBudget.pricePerManualLotto

    private fun calculateAutoPurchaseCost() = autoPurchaseCount * purchaseBudget.pricePerAutoLotto

    private fun calculateRemainingBudgetAfterManual() = purchaseBudget.amount - calculateManualPurchaseCost()
}

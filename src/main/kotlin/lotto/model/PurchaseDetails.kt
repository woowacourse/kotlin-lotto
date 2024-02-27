package lotto.model

class PurchaseDetails(
    private val budget: Budget,
    val manualPurchaseCount: Int,
    autoPurchaseCount: Int? = null,
) {
    val autoPurchaseCount: Int =
        autoPurchaseCount
            ?: (calculateRemainingBudgetAfterManual() / budget.pricePerAutoLotto)

    init {
        require(budget.totalBudget >= calculateTotalPurchaseCost())
    }

    fun calculateTotalPurchaseCost() = calculateManualPurchaseCost() + calculateAutoPurchaseCost()

    private fun calculateManualPurchaseCost() = manualPurchaseCount * budget.pricePerManualLotto

    private fun calculateAutoPurchaseCost() = autoPurchaseCount * budget.pricePerAutoLotto

    private fun calculateRemainingBudgetAfterManual() = budget.totalBudget - calculateManualPurchaseCost()
}

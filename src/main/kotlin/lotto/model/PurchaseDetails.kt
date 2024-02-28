package lotto.model

class PurchaseDetails(
    private val purchaseBudget: PurchaseBudget,
    val manualPurchaseCount: Int,
    autoPurchaseCount: Int? = null,
) {
    val autoPurchaseCount: Int =
        autoPurchaseCount ?: (calculateRemainingBudgetAfterManual() / purchaseBudget.pricePerAutoLotto)

    init {
        require(purchaseBudget.amount >= calculateManualPurchaseCost()) { BUDGET_EXCEED_ERROR_MESSAGE }
        require(purchaseBudget.amount >= calculateTotalPurchaseCost()) { BUDGET_EXCEED_ERROR_MESSAGE }
    }

    fun calculateTotalPurchaseCost() = calculateManualPurchaseCost() + calculateAutoPurchaseCost()

    private fun calculateManualPurchaseCost() = manualPurchaseCount * purchaseBudget.pricePerManualLotto

    private fun calculateAutoPurchaseCost() = autoPurchaseCount * purchaseBudget.pricePerAutoLotto

    private fun calculateRemainingBudgetAfterManual() = purchaseBudget.amount - calculateManualPurchaseCost()

    companion object {
        const val BUDGET_EXCEED_ERROR_MESSAGE = "자동 로또 구입 금액과 수동 로또 구입 금액의 합은 예산을 초과할 수 없습니다."
    }
}

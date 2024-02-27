package lotto.model

class LottoPurchaseDetails(
    private val lottoBudget: LottoBudget,
    val manualPurchaseCount: Int,
    autoPurchaseCount: Int? = null,
) {
    val autoPurchaseCount: Int =
        autoPurchaseCount
            ?: (calculateRemainingBudgetAfterManual() / lottoBudget.pricePerAutoLotto)

    init {
        require(lottoBudget.totalBudget >= calculateTotalPurchaseCost())
    }

    fun calculateTotalPurchaseCost() = calculateManualPurchaseCost() + calculateAutoPurchaseCost()

    private fun calculateManualPurchaseCost() = manualPurchaseCount * lottoBudget.pricePerManualLotto

    private fun calculateAutoPurchaseCost() = autoPurchaseCount * lottoBudget.pricePerAutoLotto

    private fun calculateRemainingBudgetAfterManual() = lottoBudget.totalBudget - calculateManualPurchaseCost()
}

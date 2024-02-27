package lotto.model

class LottoPurchasePlan(
    val lottoPurchaseBudget: LottoPurchaseBudget,
    val manualPurchaseCount: Int,
    autoPurchaseCount: Int? = null,
) {
    val autoPurchaseCount: Int =
        autoPurchaseCount
            ?: (getBudgetExcludingManualPurchase() / lottoPurchaseBudget.pricePerAutoLotto)

    init {
        require(lottoPurchaseBudget.totalBudget >= getManualPurchaseBudget() + getAutoPurchaseBudget())
    }

    private fun getBudgetExcludingManualPurchase() = lottoPurchaseBudget.totalBudget - getManualPurchaseBudget()

    private fun getManualPurchaseBudget() = manualPurchaseCount * lottoPurchaseBudget.pricePerManualLotto

    private fun getAutoPurchaseBudget() = autoPurchaseCount * lottoPurchaseBudget.pricePerAutoLotto
}

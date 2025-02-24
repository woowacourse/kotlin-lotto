package domain.model

class ManualLottoAmount(
    val value: Int,
    totalPurchaseAmount: Int,
) {
    init {
        require(value <= totalPurchaseAmount)
    }

    companion object {
        const val CANNOT_MORE_THAN_TOTAL_PURCHASE_AMOUNT = "[ERROR] 구매 가능한 개수보다 구매하려는 개수가 더 많습니다."
    }
}

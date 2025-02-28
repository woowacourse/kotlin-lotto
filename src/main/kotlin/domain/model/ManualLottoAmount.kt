package domain.model

sealed class ManualLottoAmountResult {
    data class Success(
        val amount: ManualLottoAmount,
    ) : ManualLottoAmountResult()

    data class CannotMoreThanTotalPurchaseAmount(
        val errorMsg: String,
    ) : ManualLottoAmountResult()
}

class ManualLottoAmount private constructor(
    val value: Int,
    totalPurchaseAmount: Int,
) {
    init {
        require(value <= totalPurchaseAmount) { CANNOT_MORE_THAN_TOTAL_PURCHASE_AMOUNT }
    }

    companion object {
        fun Int.toManualLottoAmountResult(totalPurchaseAmount: Int): ManualLottoAmountResult {
            if (this > totalPurchaseAmount) {
                return ManualLottoAmountResult.CannotMoreThanTotalPurchaseAmount(CANNOT_MORE_THAN_TOTAL_PURCHASE_AMOUNT)
            }
            return ManualLottoAmountResult.Success(ManualLottoAmount(this, totalPurchaseAmount))
        }

        const val CANNOT_MORE_THAN_TOTAL_PURCHASE_AMOUNT = "[ERROR] 구매 가능한 개수보다 구매하려는 개수가 더 많습니다."
    }
}

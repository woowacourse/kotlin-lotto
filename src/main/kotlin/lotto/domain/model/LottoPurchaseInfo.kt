package lotto.domain.model

class LottoPurchaseInfo(
    val amount: Amount,
    val manualLottoCount: Int,
) {
    private val totalQuantity = amount.getQuantity()

    init {
        require(totalQuantity >= manualLottoCount) { COUNT_ERROR_MESSAGE }
    }

    fun getAutoLottoQuantity(): Int = totalQuantity - manualLottoCount

    companion object {
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 수동 구매 수량은 총 구매 수량보다 클 수 없습니다."
    }
}

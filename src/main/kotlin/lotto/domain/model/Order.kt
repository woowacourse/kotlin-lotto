package lotto.domain.model

class Order(
    purchaseAmount: PurchaseAmount,
    val manualLottoAmount: Int,
) {
    private val totalPurchaseLottoAmount = purchaseAmount.calculatePurchaseLottoCount(LottoMachine.LOTTO_PRICE)
    val autoLottoAmount = totalPurchaseLottoAmount - manualLottoAmount

    init {
        require(totalPurchaseLottoAmount >= manualLottoAmount) { INVALID_MANUAL_LOTTO_AMOUNT_MESSAGE }
    }

    companion object {
        private const val INVALID_MANUAL_LOTTO_AMOUNT_MESSAGE = "구입 금액이 부족합니다."
    }
}

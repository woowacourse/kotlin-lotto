package lotto.model

import lotto.controller.LottoController.Companion.PURCHASE_UNIT

class PurchaseAmount(inputAmount: String, ticketPrice: Int = PURCHASE_UNIT) {
    private val amount: Int = inputAmount.toIntOrNull() ?: throw IllegalArgumentException(ERROR_INPUT_TYPE_MESSAGE)
    val purchasableLottoCount: Int = amount / ticketPrice
    val lottoPurchaseAmount: Int = purchasableLottoCount * ticketPrice

    init {
        require(amount >= ticketPrice) { ERROR_NUMBER_RANGE_MESSAGE }
    }

    companion object {
        private const val ERROR_NUMBER_RANGE_MESSAGE = "최소 ${PURCHASE_UNIT}원 이상 입력해주세요."
        private const val ERROR_INPUT_TYPE_MESSAGE = "숫자만 입력 가능합니다."
    }
}

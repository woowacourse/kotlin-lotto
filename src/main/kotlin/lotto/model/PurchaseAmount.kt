package lotto.model

import lotto.constants.GameConstant.PURCHASE_UNIT

class PurchaseAmount(private val ticketPrice: Int = PURCHASE_UNIT) {
    fun getAmount(inputMoney: String): Int {
        val amount =
            inputMoney.toIntOrNull()
                ?: throw IllegalArgumentException(ERROR_INPUT_TYPE_MESSAGE)
        validateNumberRange(amount)
        return amount
    }

    private fun validateNumberRange(amount: Int) {
        require(amount >= ticketPrice) { ERROR_NUMBER_RANGE_MESSAGE }
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."
        private const val ERROR_NUMBER_RANGE_MESSAGE = "$ERROR_PREFIX${PURCHASE_UNIT}원 이상의 값만 입력 가능합니다."
    }
}

package lotto.model

class PurchaseAmount(private val inputMoney: String) {
    init {
        val amount = inputMoney.toIntOrNull()
            ?: throw IllegalArgumentException(ERROR_INPUT_TYPE_MESSAGE)
        validateNumberRange(amount)
    }

    private fun validateNumberRange(amount: Int) {
        require(amount > PURCHASE_UNIT) { ERROR_NUMBER_RANGE_MESSAGE }
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."
        private const val ERROR_NUMBER_RANGE_MESSAGE = "${ERROR_PREFIX}1000원 이상의 값만 입력 가능합니다."
        private const val PURCHASE_UNIT = 1000
    }
}

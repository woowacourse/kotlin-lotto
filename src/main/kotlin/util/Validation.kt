package lotto.util

object Validation {
    private const val ERROR_PREFIX = "[ERROR] "
    private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."
    private const val PURCHASE_UNIT = 1000
    private const val ERROR_AMOUNT_RANGE_MESSAGE = "${ERROR_PREFIX}1000원 이상의 값만 입력 가능합니다."

    fun validateIsDigit(inputMoney: String) {
        require(inputMoney.all { it.isDigit() }) { ERROR_INPUT_TYPE_MESSAGE }
    }

    fun validateAmountRange(amount: Int) {
        require(amount >= PURCHASE_UNIT) { ERROR_AMOUNT_RANGE_MESSAGE }
    }
}

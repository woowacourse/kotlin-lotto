package lotto.model

class PurchaseAmount(val money: Int) {
    init {
        validateNumberRange(money)
    }

    constructor(money: String) : this(validateInputMoney(money))

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."
        private const val ERROR_NUMBER_RANGE_MESSAGE = "${ERROR_PREFIX}1000원 이상의 값만 입력 가능합니다."
        private const val PURCHASE_UNIT = 1000

        private fun validateInputMoney(inputMoney: String): Int {
            validateIsDigit(inputMoney)
            validateNumberRange(inputMoney.toInt())
            return inputMoney.toInt()
        }

        private fun validateIsDigit(inputMoney: String) {
            require(inputMoney.all { it.isDigit() }) { ERROR_INPUT_TYPE_MESSAGE }
        }

        private fun validateNumberRange(amount: Int) {
            require(amount >= PURCHASE_UNIT) { ERROR_NUMBER_RANGE_MESSAGE }
        }
    }
}

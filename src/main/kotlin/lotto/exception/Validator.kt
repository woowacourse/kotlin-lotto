package lotto.exception

class Validator {
    fun checkInputMoney(input: String) {
        val number = input.toIntOrNull()
        require(number != null) { MONEY_NOT_NUMBER_ERROR }
        require(number > 0) { MONEY_NEGATIVE_NUMBER_ERROR }
        require(number % 1000 == 0) { MONEY_UNIT_ERROR }
    }

    companion object {
        private const val MONEY_NOT_NUMBER_ERROR = "금액은 숫자여야 합니다."
        private const val MONEY_NEGATIVE_NUMBER_ERROR = "금액은 양수여야 합니다."
        private const val MONEY_UNIT_ERROR = "금액은 1000원 단위여야 합니다."
    }
}
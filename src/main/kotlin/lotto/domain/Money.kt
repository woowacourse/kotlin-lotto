package lotto.domain

data class Money(val value: Int) {
    init {
        require(value > 0) { MONEY_NEGATIVE_NUMBER_ERROR }
        require(value % MONEY_UNIT == 0) { MONEY_UNIT_ERROR }
    }

    companion object {
        const val MONEY_UNIT = 1000
        private const val MONEY_NOT_NUMBER_ERROR = "금액은 숫자여야 합니다."
        private const val MONEY_UNIT_ERROR = "금액은 1000원 단위여야 합니다."
        private const val MONEY_NEGATIVE_NUMBER_ERROR = "금액은 양수여야 합니다."

        fun validateInputMoney(input: String) {
            require(input.toIntOrNull() != null) { MONEY_NOT_NUMBER_ERROR }
        }
    }
}

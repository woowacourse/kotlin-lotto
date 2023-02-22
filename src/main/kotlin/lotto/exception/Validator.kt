package lotto.exception

object Validator {
    private const val MONEY_NOT_NUMBER_ERROR = "금액은 숫자여야 합니다."
    private const val BONUS_NOT_NUMBER_ERROR = "보너스 볼은 숫자여야 합니다."
    private const val LOTTO_COUNT_NOT_NUMBER_ERROR = "수동으로 구매할 로또 수는 숫자여야 합니다."
    private const val LOTTO_COUNT_RANGE_ERROR = "수동으로 구매할 로또 수가 금액보다 큽니다."

    fun checkInputMoney(input: String) {
        val number = input.toIntOrNull()
        require(number != null) { MONEY_NOT_NUMBER_ERROR }
    }

    fun checkInputBonusNumber(input: String) {
        require(input.toIntOrNull() != null) { BONUS_NOT_NUMBER_ERROR }
    }

    fun checkInputManualCount(input: String, totalCount: Int) {
        val number = input.toIntOrNull()
        require(number != null) { LOTTO_COUNT_NOT_NUMBER_ERROR }
        require(number <= totalCount) { LOTTO_COUNT_RANGE_ERROR }
    }
}

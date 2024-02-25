package util

class InputViewValidator {
    fun validateExpense(expense: Int): Int {
        require(expense >= MIN_MONEY) { EXCEPTION_MONEY_RANGE }
        require(expense % LOTTO_UNIT_PRICE == 0) { EXCEPTION_PURCHASE_EXPENSE }

        return expense
    }

    companion object {
        private const val MIN_MONEY = 0
        private const val EXCEPTION_MONEY_RANGE = "Money는 ${MIN_MONEY}원 이상이어야 합니다."
        private const val LOTTO_UNIT_PRICE = 1000
        private const val EXCEPTION_PURCHASE_EXPENSE = "로또 구매 금액은 1000단위의 정수여야 합니다."
        private const val EXCEPTION_WINNING_NUMBER = "당첨 번호는 정수여야 합니다."
        private const val EXCEPTION_BONUS_NUMBER = "보너스 번호는 정수여야 합니다."
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val EXCEPTION_NUM_RANGE = "로또 번호는 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER}까지만 가능합니다."
        private val LOTTO_NUM_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
    }
}

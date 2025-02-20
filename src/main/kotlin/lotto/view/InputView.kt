package lotto.view

class InputView {
    private fun validateAmount(amount: String): Int {
        require(amount.toIntOrNull() != null) {
            IllegalArgumentException(ERROR_INVALID_AMOUNT)
        }
        require(amount.toInt() % LOTTO_AMOUNT == ZERO) {
            IllegalArgumentException(ERROR_INVALID_DIVISION)
        }
        return amount.toInt()
    }

    private fun validateWinningNumbers(winningNumbers: List<String>): List<Int> {
        require(winningNumbers.all { it.toIntOrNull() != null }) {
            IllegalArgumentException(ERROR_INVALID_WINNING_TYPE)
        }
        return winningNumbers.map { it.toInt() }
    }

    fun inputPurchaseAmount(): Int {
        println(MESSAGE_INPUT_AMOUNT)
        val purchaseAmount = readln()
        return validateAmount(purchaseAmount)
    }

    fun inputWinningNumbers(): List<Int> {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        val winningNumbers = readln().split(COMMA).map { it.trim() }
        return validateWinningNumbers(winningNumbers)
    }

    fun inputBonusNumber(): Int {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val bonusNumber = readln()
        return bonusNumber.toInt()
    }

    companion object {
        private const val MESSAGE_INPUT_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val ERROR_INVALID_AMOUNT = "구입금액은 정수를 입력해야 합니다."
        private const val ERROR_INVALID_DIVISION = "구입금액은 1000의 배수여야 합니다."
        private const val ERROR_INVALID_WINNING_TYPE = "당첨번호는 정수를 입력해야 합니다."

        private const val ZERO = 0
        private const val LOTTO_AMOUNT = 1_000
        private const val COMMA = ","
    }
}

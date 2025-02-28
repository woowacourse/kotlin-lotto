package lotto.view

class InputView {
    fun validateAmount(amount: String): Int {
        require(amount.toIntOrNull() != null) { ERROR_INVALID_AMOUNT }
        return amount.toInt()
    }

    fun validateManualCount(count: String): Int {
        require(count.toIntOrNull() != null) { ERROR_INVALID_COUNT_TYPE }
        return count.toInt()
    }

    fun validateManualNumbers(manualNumbers: List<String>): List<Int> {
        require(manualNumbers.all { it.toIntOrNull() != null }) { ERROR_INVALID_WINNING_TYPE }
        return manualNumbers.map { it.toInt() }
    }

    fun validateWinningNumbers(winningNumbers: List<String>): List<Int> {
        require(winningNumbers.all { it.toIntOrNull() != null }) { ERROR_INVALID_WINNING_TYPE }
        return winningNumbers.map { it.toInt() }
    }

    fun validateBonusNumber(bonusNumber: String): Int {
        require(bonusNumber.toIntOrNull() != null) { ERROR_BONUS_TYPE }
        return bonusNumber.toInt()
    }

    fun inputPurchaseAmount(): Int {
        println(MESSAGE_INPUT_AMOUNT)
        val purchaseAmount = readln().trim()
        return validateAmount(purchaseAmount)
    }

    fun inputManualCount(): Int {
        println(MESSAGE_INPUT_MANUAL_COUNT)
        val manualCount = readln().trim()
        return validateManualCount(manualCount)
    }

    fun inputManualNumbers(): List<Int> {
        val manualNumbers = readln().split(COMMA).map { it.trim() }
        return validateManualNumbers(manualNumbers)
    }

    fun inputWinningNumbers(): List<Int> {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        val winningNumbers = readln().split(COMMA).map { it.trim() }
        return validateWinningNumbers(winningNumbers)
    }

    fun inputBonusNumber(): Int {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val bonusNumber = readln().trim()
        return validateBonusNumber(bonusNumber)
    }

    companion object {
        private const val MESSAGE_INPUT_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val ERROR_INVALID_AMOUNT = "구입금액은 정수를 입력해야 합니다."
        private const val ERROR_INVALID_COUNT_TYPE = "구매할 로또 수는 정수를 입력해야 합니다."
        private const val ERROR_INVALID_WINNING_TYPE = "당첨번호는 정수를 입력해야 합니다."
        private const val ERROR_BONUS_TYPE = "보너스 번호는 정수를 입력해야 합니다."

        private const val COMMA = ","
    }
}

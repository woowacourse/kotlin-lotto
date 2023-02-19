package lotto.view

import kotlin.IllegalArgumentException

class InputView {
    fun readPurchaseAmount(): Int {
        return try {
            println(PURCHASE_AMOUNT_GUIDE)
            val amount = readLine().toIntOrNull()
            requireNotNull(amount) { PURCHASE_AMOUNT_TYPE_ERROR }
            amount
        } catch (e: IllegalArgumentException) {
            printError(e.message ?: "")
            readPurchaseAmount()
        }
    }

    fun readWinningNumbers(): List<Int> {
        return try {
            println(WINNING_NUMBERS_GUIDE)
            val numbers = readLine().split(", ").mapNotNull { it.toIntOrNull() }
            require(numbers.size == 6) { WINNING_NUMBERS_TYPE_ERROR }
            numbers
        } catch (e: IllegalArgumentException) {
            printError(e.message ?: "")
            readWinningNumbers()
        }
    }

    fun readBonusNumber(): Int {
        return try {
            println(BONUS_NUMBERS_GUIDE)
            val number = readLine().toIntOrNull()
            requireNotNull(number) { BONUS_NUMBERS_TYPE_ERROR }
            number
        } catch (e: IllegalArgumentException) {
            printError(e.message ?: "")
            readBonusNumber()
        }
    }

    private fun readLine(): String {
        return readln()
    }

    private fun printError(message: String) {
        println("$ERROR_HEADER $message")
    }

    companion object {
        private const val BONUS_NUMBERS_GUIDE = "보너스 볼을 입력해 주세요."
        private const val BONUS_NUMBERS_TYPE_ERROR = "보너스 번호는 정수여야 합니다."
        private const val ERROR_HEADER = "[ERROR]"
        private const val PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요."
        private const val PURCHASE_AMOUNT_TYPE_ERROR = "구매금액은 정수여야 합니다."
        private const val WINNING_NUMBERS_GUIDE = "지난 주 당첨 번호를 입력해 주세요."
        private const val WINNING_NUMBERS_TYPE_ERROR = "당첨 번호는 정수여야 합니다."
    }
}

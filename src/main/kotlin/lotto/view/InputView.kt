package lotto.view

import java.lang.IllegalArgumentException

class InputView {
    fun readPurchaseAmount(): Int {
        var correctInput = false
        var input = ""
        while (!correctInput) {
            println(PURCHASE_AMOUNT_GUIDE)
            input = readLine()
            correctInput = isInteger(input, PURCHASE_AMOUNT_TYPE_ERROR)
        }
        return input.toInt()
    }

    fun readWinningNumbers(): List<Int> {
        var correctInput = false
        var inputs = listOf<String>()
        while (!correctInput) {
            println(WINNING_NUMBERS_GUIDE)
            val input = readLine()
            inputs = input.split(", ")
            correctInput = isIntegerList(inputs, WINNING_NUMBERS_TYPE_ERROR)
        }
        return inputs.map { it.toInt() }
    }

    fun readBonusNumber(): Int {
        var correctInput = false
        var input = ""
        while (!correctInput) {
            println(BONUS_NUMBERS_GUIDE)
            input = readLine()
            correctInput = isInteger(input, BONUS_NUMBERS_TYPE_ERROR)
        }
        return input.toInt()
    }

    private fun readLine(): String {
        return readln()
    }

    private fun isInteger(input: String, message: String): Boolean {
        return try {
            requireNotNull(input.toIntOrNull()) { message }
            true
        } catch (e: IllegalArgumentException) {
            printError(e.message ?: "")
            false
        }
    }

    private fun isIntegerList(inputs: List<String>, message: String): Boolean {
        val correctCount = inputs.map { isInteger(it, message) }.count { it }
        return correctCount == inputs.size
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

package lotto.view

import java.lang.IllegalArgumentException

class InputView {
    fun readPurchaseAmount(): Int {
        var correctInput = false
        var input = ""
        while (!correctInput) {
            println(PURCHASE_AMOUNT_GUIDE)
            input = readLine()
            correctInput = isInteger(input)
        }
        return input.toInt()
    }

    private fun readLine(): String {
        return readln()
    }

    private fun isInteger(input: String): Boolean {
        return try {
            requireNotNull(input.toIntOrNull()) { PURCHASE_AMOUNT_TYPE_ERROR }
            true
        } catch (e: IllegalArgumentException) {
            printError(e.message ?: "")
            false
        }
    }

    private fun printError(message: String) {
        println("$ERROR_HEADER $message")
    }

    companion object {
        private const val ERROR_HEADER = "[ERROR]"
        private const val PURCHASE_AMOUNT_GUIDE = "구입금액을 입력해 주세요."
        private const val PURCHASE_AMOUNT_TYPE_ERROR = "구매금액은 정수여야 합니다."
    }
}

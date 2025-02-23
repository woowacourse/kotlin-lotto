package lotto.view

class InputView {
    fun getPurchaseAmount(): String {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
        val input = readln()
        validateNotNull(input)
        validateInteger(input)
        return input
    }

    fun getWinningNumber(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        val input = readln()
        validateNotNull(input)
        validateListInteger(input)
        return input
    }

    fun getBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val input = readln()
        validateNotNull(input)
        validateInteger(input)
        return input
    }

    private fun validateListInteger(input: String) {
        require(input.split(",").all { it.toIntOrNull() != null }) { ERROR_NOT_FORMATTED }
    }

    private fun validateInteger(input: String) {
        require(input.toIntOrNull() != null) { ERROR_NOT_INTEGER }
    }

    private fun validateNotNull(input: String) {
        require(input.isNotEmpty()) { ERROR_NOT_NULL }
    }

    companion object {
        const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        const val ERROR_NOT_FORMATTED = "[ERROR] 당첨 번호는 숫자, 구분자는 콤마(,)만 가능합니다."
        const val ERROR_NOT_INTEGER = "[ERROR] 숫자만 입력 가능합니다."
        const val ERROR_NOT_NULL = "[ERROR] 입력이 없을 수 없습니다."
    }
}

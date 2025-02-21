package lotto.view

object InputMessage {
    const val MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    const val WINNING_LOTTO_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    const val BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
}

class InputView {
    fun getSingleNumber(message: String): Int {
        println(message)
        val input = readln()
        validateSingleInput(input)
        return input.toInt()
    }

    fun getMultipleNumber(message: String): List<Int> {
        println(message)
        val input = readln()
        validateMultipleInput(input)
        return input.split(",").map { it.trim().toInt() }
    }

    private fun validateSingleInput(input: String) {
        validateEmptyInput(input)
        validateNumber(input)
    }

    private fun validateMultipleInput(input: String) {
        validateEmptyInput(input)
        input.split(",").forEach { validateNumber(it.trim()) }
    }

    private fun validateEmptyInput(input: String) {
        require(input.trim().isNotEmpty()) {
            "[ERROR] 입력이 존재하지 않습니다."
        }
    }

    private fun validateNumber(input: String) {
        require(input.toIntOrNull() != null) {
            "[ERROR] 입력이 정수가 아닙니다: $input"
        }
    }
}

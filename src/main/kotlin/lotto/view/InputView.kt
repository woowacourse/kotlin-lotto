package lotto.view

object InputView {
    private const val LOTTO_NUMBERS_DELIMITER = ","
    private const val ERROR_MESSAGE_INVALID_INPUT_STATE = "정상적으로 입력되지 않았습니다."
    private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
    private const val ERROR_MESSAGE_INVALID_WINNING_NUMBERS = "양식에 맞게 입력해주세요. (1, 2, 3, 4, 5, 6)"

    fun readPrice(): Int {
        val input: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val price: Int = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return price
    }

    fun readWinningNumbers(): List<Int> {
        val lottoInput: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val wonLotto: List<Int> =
            lottoInput.split(LOTTO_NUMBERS_DELIMITER).map { number: String ->
                number.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_WINNING_NUMBERS)
            }
        return wonLotto
    }

    fun readNumber(): Int {
        val input: String = readLine() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT_STATE)
        val number: Int =
            input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return number
    }
}

package lotto.view

object InputView {
    fun readPrice(): Int {
        val priceInput: String = readln()
        val price: Int = priceInput.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return price
    }

    fun readWinningNumbers(): List<Int> {
        val winningNumbersInput: String = readln()
        val winningNumbers: List<Int> =
            winningNumbersInput.split(LOTTO_NUMBERS_DELIMITER).map { number: String ->
                number.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_WINNING_NUMBERS)
            }
        return winningNumbers
    }

    fun readBonusNumber(): Int {
        val bonusNumberInput: String = readln()
        val bonusNumber: Int =
            bonusNumberInput.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return bonusNumber
    }

    private const val LOTTO_NUMBERS_DELIMITER = ","
    private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
    private const val ERROR_MESSAGE_INVALID_WINNING_NUMBERS = "양식에 맞게 입력해주세요. (1, 2, 3, 4, 5, 6)"
}

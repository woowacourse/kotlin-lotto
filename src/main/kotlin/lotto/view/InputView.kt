package lotto.view

object InputView {
    private const val LOTTO_NUMBERS_DELIMITER = ","
    private const val ERROR_MESSAGE_INPUT_NOT_A_NUMBER = "숫자를 입력해주세요."
    private const val ERROR_MESSAGE_INVALID_WINNING_NUMBERS = "양식에 맞게 입력해주세요. (1, 2, 3, 4, 5, 6)"

    fun readPrice(): Int {
        val input: String = readln()
        val price: Int = input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return price
    }

    fun readWinningNumbers(): List<Int> {
        val lottoInput: String = readln()
        val wonLotto: List<Int> =
            lottoInput.split(LOTTO_NUMBERS_DELIMITER).map { number: String ->
                number.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INVALID_WINNING_NUMBERS)
            }
        return wonLotto
    }

    fun readBonusNumber(): Int {
        val bonusInput: String = readln()
        val bonusNumber: Int =
            bonusInput.toIntOrNull() ?: throw IllegalArgumentException(ERROR_MESSAGE_INPUT_NOT_A_NUMBER)
        return bonusNumber
    }
}

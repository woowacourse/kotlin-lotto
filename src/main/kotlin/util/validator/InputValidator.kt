package util.validator

class InputValidator {
    fun validateWinningNumbersIsNumeric(value: List<String>): List<Int> {
        val winningNumbers = value.map { it.trim() }
        winningNumbers.forEach { number -> number.toInt() }

        return winningNumbers.map { it.toInt() }
    }

    companion object {
        private const val NOT_NUMERIC_EXCEPTION_MESSAGE = "[ERROR] 입력값이 숫자가 아닙니다."
    }
}

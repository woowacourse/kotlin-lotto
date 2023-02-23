package util.validator

class InputValidator {
    fun validateWinningNumbersIsNumeric(value: List<String>): List<Int>? {
        return value.map { it.trim().toIntOrNull() ?: return null }
    }

    companion object {
        private const val NOT_NUMERIC_EXCEPTION_MESSAGE = "[ERROR] 입력값이 숫자가 아닙니다."
    }
}

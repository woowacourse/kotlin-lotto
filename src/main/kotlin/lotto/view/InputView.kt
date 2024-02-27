package lotto.view

import lotto.constants.GameConstant.SPLIT_DELIMITER

class InputView {
    fun readPurchaseAmount(): String = validateNullInput(readlnOrNull())

    fun readWinningNumbers(): List<Int> {
        val winningNumbers = validateNullInput(readlnOrNull()).replace(" ", "")
        return winningNumbers.split(SPLIT_DELIMITER).map { it.validateAndConvertDigit() }
    }

    fun readWinningBonusNumber(): Int {
        val bonusNumber = validateNullInput(readlnOrNull())
        return bonusNumber.validateAndConvertDigit()
    }

    private fun validateNullInput(input: String?): String {
        return input ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)
    }

    private fun String.validateAndConvertDigit(): Int {
        return toIntOrNull() ?: throw IllegalArgumentException(ERROR_INPUT_TYPE_MESSAGE)
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."
        private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."
    }
}

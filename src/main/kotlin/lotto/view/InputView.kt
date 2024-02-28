package lotto.view

class InputView {
    fun readPurchaseAmount(): Int = validateNullInput(readlnOrNull()).validateAndConvertDigit()

    fun readManualLottoCount(): Int = validateNullInput(readlnOrNull()).validateAndConvertDigit()

    fun readManualLottoNumbers(manualLottoCount: Int): List<List<String>> {
        val manualLotto: MutableList<List<String>> = mutableListOf()
        repeat(manualLottoCount) {
            val manualLottod =
                validateNullInput(readlnOrNull()).replace(" ", "").split(SPLIT_DELIMITER).map(String::trim)
            manualLotto.add(manualLottod)
        }
        return manualLotto
    }

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
        private const val SPLIT_DELIMITER = ","
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."
        private const val ERROR_INPUT_TYPE_MESSAGE = "${ERROR_PREFIX}숫자만 입력 가능합니다."
    }
}

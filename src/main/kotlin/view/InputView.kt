package lotto.view

class InputView {
    fun readPurchaseAmount(): String = validateNullInput(readlnOrNull())

    fun readNumberOfManualLottos(): String {
        return validateNullInput(readlnOrNull())
    }

    fun readManualLottoNumber(numberOfManualLottos: Int): List<List<String>> {
        val manualLottos = mutableListOf<List<String>>()
        repeat(numberOfManualLottos) {
            manualLottos.add(validateNullInput(readlnOrNull()).replace(" ", "").split(SPLIT_DELIMITER))
        }
        return manualLottos
    }

    fun readWinningNumbers(): List<String> = validateNullInput(readlnOrNull()).replace(" ", "").split(SPLIT_DELIMITER)

    fun readWinningBonusNumber(): String {
        val bonusNumber = validateNullInput(readlnOrNull())
        return bonusNumber
    }

    private fun validateNullInput(input: String?): String {
        return input ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."
        private const val SPLIT_DELIMITER = ","
    }
}

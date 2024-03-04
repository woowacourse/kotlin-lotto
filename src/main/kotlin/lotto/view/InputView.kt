package lotto.view

class InputView {
    fun readManualLottoCount(): String {
        println("\n수동으로 구매할 로또 수를 입력해 주세요.")
        return validateNullInput(readlnOrNull())
    }

    fun readWinningNumbers(): List<String> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        val winningNumbers = validateNullInput(readlnOrNull()).replace(" ", "")
        return winningNumbers.split(SPLIT_DELIMITER).map { it }
    }

    fun readWinningBonusNumber(): String {
        println("\n보너스 볼을 입력해 주세요.")
        return validateNullInput(readlnOrNull())
    }

    private fun validateNullInput(input: String?): String {
        return input ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)
    }

    companion object {
        private const val SPLIT_DELIMITER = ","
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."
    }
}

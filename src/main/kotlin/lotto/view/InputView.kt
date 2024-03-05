package lotto.view

class InputView {
    fun readPurchaseAmount(): String? {
        println("구입금액을 입력해 주세요.")
        return runCatching {
            validateNullInput(readlnOrNull())
        }.getOrNull()
    }

    fun readManualLottoCount(): String? {
        println("\n수동으로 구매할 로또 수를 입력해 주세요.")
        return runCatching {
            validateNullInput(readlnOrNull())
        }.getOrNull()
    }

    fun readWinningNumbers(): List<String> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return validateNullInput(readlnOrNull()).split(SPLIT_DELIMITER)
    }

    fun readWinningBonusNumber(): String {
        println("\n보너스 볼을 입력해 주세요.")
        return validateNullInput(readlnOrNull())
    }

    private fun validateNullInput(input: String?): String {
        if (input.isNullOrBlank()) {
            println(ERROR_EMPTY_INPUT_MESSAGE)
            throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)
        }
        return input
    }

    companion object {
        private const val SPLIT_DELIMITER = ","
        private const val ERROR_PREFIX = "[ERROR] "
        private const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."
    }
}

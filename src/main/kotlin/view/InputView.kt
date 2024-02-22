package lotto.view


class InputView {
    fun readPurchaseAmount(): String =
        readlnOrNull()?.trim() ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)

    fun readWinningNumbers(): String =
        readlnOrNull()?.trim() ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)

    fun readWinningBonusNumber(): String =
        readlnOrNull()?.trim() ?: throw IllegalArgumentException(ERROR_EMPTY_INPUT_MESSAGE)

    companion object {
        private const val ERROR_PREFIX = "[ERROR] "
        const val ERROR_EMPTY_INPUT_MESSAGE = "${ERROR_PREFIX}입력값이 없습니다."
    }
}

package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        println(READ_PURCHASE_AMOUNT_MESSAGE)
        return readOnlyInt()
    }

    fun readWinningNumbers(): List<Int> {
        println(READ_WINNING_NUMBERS_MESSAGE)
        return readNumbers()
    }

    fun readBonusNumber(): Int {
        println(READ_BONUS_NUMBER_MESSAGE)
        return readOnlyInt()
    }

    fun readManualLottoAmount(): Int {
        println(READ_MANUAL_LOTTO_AMOUNT_MESSAGE)
        return readOnlyInt()
    }

    fun readManualLottoNumbers(): List<Int> {
        println(READ_MANUAL_LOTTO_NUMBERS_MESSAGE)
        return readNumbers()
    }

    private fun readOnlyInt(): Int {
        return requireNotNull(readLineWithEmptyValidate().toIntOrNull()) { INVALID_NUMBER_MESSAGE }
    }

    private fun readNumbers(): List<Int> {
        val numbersInput = readLineWithEmptyValidate().split(LOTTO_NUMBERS_DELIMITER).map { it.trim() }
        return numbersInput.map { requireNotNull(it.toIntOrNull()) { INVALID_NUMBER_MESSAGE } }
    }

    private fun readLineWithEmptyValidate(): String {
        val input = readlnOrNull() ?: ""
        require(input.isBlank().not()) { EMPTY_INPUT_MESSAGE }
        return input.trim()
    }

    companion object {
        private const val LOTTO_NUMBERS_DELIMITER = ','
        private const val READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
        private const val READ_WINNING_NUMBERS_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val READ_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val READ_MANUAL_LOTTO_AMOUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val READ_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."

        private const val INVALID_NUMBER_MESSAGE = "숫자만 입력해 주세요.(공백 포함 x)"
        private const val EMPTY_INPUT_MESSAGE = "빈 문자열입니다."
    }
}

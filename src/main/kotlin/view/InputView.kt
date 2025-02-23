package view

class InputView {
    fun readPurchasePrice(): Int {
        return readInt(MESSAGE_INPUT_PURCHASE_PRICE)
    }

    fun readPassivityLottoCount(): Int {
        return readInt(MESSAGE_INPUT_PASSIVITY_LOTTO)
    }

    fun readWinningNumbers(): List<Int> {
        return readIntList(MESSAGE_INPUT_WINNING_NUMBER)
    }

    fun readBonusNumber(): Int {
        return readInt(MESSAGE_INPUT_BONUS_NUMBER)
    }

    private fun readInt(message: String): Int {
        println(message)
        val input = readln()
        return validateAndParseInt(input)
    }

    private fun readIntList(message: String): List<Int> {
        println(message)
        val input = readln()
        return validateAndParseToIntList(input)
    }

    private fun validateAndParseInt(input: String): Int {
        validateEmpty(input)
        validateNumeric(input)
        return input.toInt()
    }

    private fun validateAndParseToIntList(input: String): List<Int> {
        validateEmpty(input)
        return input.split(",")
            .map { number -> validateAndParseInt(number) }
    }

    private fun validateEmpty(input: String) {
        require(input.isNotBlank()) { EMPTY_INPUT_ERROR }
    }

    private fun validateNumeric(input: String) {
        require(input.toIntOrNull() != null) { NOT_NUMERIC_ERROR }
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_PASSIVITY_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "\n보너스 볼을 입력해 주세요."

        private const val EMPTY_INPUT_ERROR = "빈 값을 입력하셨습니다."
        private const val NOT_NUMERIC_ERROR = "숫자만 입력해주세요."
    }
}

package view

class InputView {
    fun readPurchasePrice(): Int = readInt(MESSAGE_INPUT_PURCHASE_PRICE)

    fun readManualLottoAmount(): Int = readInt(MESSAGE_INPUT_MANUAL_LOTTO_AMOUNT)

    fun readManualLottoNumbers(): List<Int> = readIntList()

    fun readWinningNumbers(): List<Int> = readIntList(MESSAGE_INPUT_WINNING_NUMBER)

    fun readBonusNumber(): Int = readInt(MESSAGE_INPUT_BONUS_NUMBER)

    private fun readInt(message: String): Int {
        while (true) {
            println(message)
            val input = readln()
            val number = validateAndParseInt(input)
            if (number != null) return number
        }
    }

    private fun readIntList(message: String? = null): List<Int> {
        while (true) {
            message?.let { println(message) }
            val input = readln()
            val result = validateAndParseToIntList(input)
            if (input.split(",").size == result.size) return result
        }
    }

    private fun validateAndParseInt(input: String): Int? {
        if (validateEmpty(input)) return null
        val value = input.toIntOrNull()
        if (value == null) {
            println(NOT_NUMERIC_ERROR)
        }
        return value
    }

    private fun validateAndParseToIntList(input: String): List<Int> {
        return input.split(",")
            .mapNotNull { validateAndParseInt(it) }
    }

    private fun validateEmpty(input: String): Boolean {
        if (input.isBlank()) {
            println(EMPTY_INPUT_ERROR)
            return true
        }
        return false
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_AMOUNT = "수동으로 구매할 로또 수를 입력해 주세요."

        private const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "\n보너스 볼을 입력해 주세요."

        private const val EMPTY_INPUT_ERROR = "빈 값을 입력하셨습니다."
        private const val NOT_NUMERIC_ERROR = "숫자만 입력해주세요."
    }
}

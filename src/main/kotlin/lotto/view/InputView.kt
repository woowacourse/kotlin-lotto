package lotto.view

class InputView {
    fun getManualLottoCount(): String {
        println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
        val input = readln()
        validateNumeric(input)
        return input
    }

    fun getPurchaseAmount(): String {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
        val input = readln()
        validateNumeric(input)
        return input
    }

    fun getWinningNumber(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        val input = readln()
        validateNumberList(input.split(",").map { it.trim() })
        return input
    }

    fun getBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val input = readln()
        validateNumeric(input)
        return input
    }

    private fun validateNumeric(input: String) {
        input.toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
    }

    private fun validateNumberList(numbers: List<String>) {
        numbers.forEach { number ->
            number.toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
        }
    }

    companion object {
        private const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val ERROR_NOT_NUMBER = "[ERROR] 양수만 입력 가능합니다."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
    }
}

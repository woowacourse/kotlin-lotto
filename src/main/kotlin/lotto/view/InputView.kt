package lotto.view

class InputView {
    fun getPurchaseAmount(): Int {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
        val input = readln()
        if (input.toIntOrNull() == null) {
            println(ERROR_NOT_INVALID_INPUT)
            return getPurchaseAmount()
        }
        return input.toInt()
    }

    fun getManualLottoCount(): Int {
        println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
        val input = readln()
        if (input.toIntOrNull() == null) {
            println(ERROR_NOT_INVALID_INPUT)
            return getManualLottoCount()
        }
        return input.toInt()
    }

    fun getManualLottoTickets() {
        println(MESSAGE_INPUT_MANUAL_LOTTO_TICKETS)
    }

    fun getManualLotto(): String {
        val input = readln()
        if (!validateIntegers(input)) {
            return getManualLotto()
        }
        return input
    }

    fun getWinningNumber(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        val input = readln()
        if (validateIntegers(input)) {
            return input
        }
        return getWinningNumber()
    }

    private fun validateIntegers(input: String): Boolean {
        return input.split(SEPARATOR).map { it.toIntOrNull() }.all { it != null }
    }

    fun getBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val input = readln()
        if (validateInteger(input)) {
            return input
        }
        return getBonusNumber()
    }

    private fun validateInteger(input: String): Boolean {
        runCatching { input.toInt() }
            .onFailure {
                println(ERROR_NOT_INTEGER)
                return false
            }
        return true
    }

    companion object {
        const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입 금액을 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_TICKETS = "\n수동으로 구매할 번호를 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        const val ERROR_NOT_INVALID_INPUT = "[ERROR] 입력이 올바르지 않습니다. 다시 입력해주세요"
        const val ERROR_NOT_INTEGER = "[ERROR] 숫자만 입력 가능합니다."

        const val SEPARATOR = ","
    }
}

package lotto.view

class InputView {
    fun getPurchaseAmount(): String {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
        val input = readln()
        if (validateInteger(input)) {
            return input
        }
        return getPurchaseAmount()
    }

    fun getManualLottoCount(): String {
        println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
        val input = readln()
        if (validateInteger(input)) {
            return input
        }
        return getManualLottoCount()
    }

    fun getManualLottoTickets(count: Int): String {
        println(MESSAGE_INPUT_MANUAL_LOTTO_TICKETS)
        repeat(count) {
            val input = readln()
            if (validateInteger(input)) {
                return input
            }
        }
        return getManualLottoTickets(count)
    }

    fun getWinningNumber(): String {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        val input = readln()
        if (validateListInteger(input)) {
            return input
        }
        return getWinningNumber()
    }

    fun getBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val input = readln()
        if (validateInteger(input)) {
            return input
        }
        return getBonusNumber()
    }

    private fun validateListInteger(input: String): Boolean {
        runCatching { input.split(SEPARATOR).map { it.toInt() } }
            .onFailure {
                println(ERROR_NOT_FORMATTED)
                return false
            }
        return true
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
        const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_TICKETS = "\n수동으로 구매할 번호를 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        const val SEPARATOR = ","

        const val ERROR_NOT_FORMATTED = "[ERROR] 구분자는 콤마(,)만 가능합니다."
        const val ERROR_NOT_INTEGER = "[ERROR] 숫자만 입력 가능합니다."
    }
}

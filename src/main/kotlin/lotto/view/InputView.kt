package lotto.view

import lotto.view.InputMessage.BONUS_NUMBER_INPUT_MESSAGE
import lotto.view.InputMessage.COUNT_INPUT_MESSAGE
import lotto.view.InputMessage.ERROR_BONUS_MESSAGE
import lotto.view.InputMessage.ERROR_COUNT_MESSAGE
import lotto.view.InputMessage.ERROR_LOTTO_MESSAGE
import lotto.view.InputMessage.LOTTO_INPUT_MESSAGE
import lotto.view.InputMessage.MONEY_INPUT_MESSAGE
import lotto.view.InputMessage.WINNING_LOTTO_INPUT_MESSAGE

object InputMessage {
    const val MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    const val WINNING_LOTTO_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    const val BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
    const val COUNT_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
    const val LOTTO_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    const val ERROR_LOTTO_MESSAGE = "로또 번호가 잘못되었습니다. 다시 입력해 주세요"
    const val ERROR_BONUS_MESSAGE = "보너스 번호가 잘못되었습니다. 다시 입력해 주세요."
    const val ERROR_COUNT_MESSAGE = "수동 로또 구매 횟수가 금액을 초과합니다. 다시 입력해 주세요"
}

class InputView {
    fun getMoney(): Int = getSingleNumber(MONEY_INPUT_MESSAGE)

    fun getWinningLotto(): List<Int> = getMultipleNumber(WINNING_LOTTO_INPUT_MESSAGE)

    fun getBonusNumber(): Int = getSingleNumber(BONUS_NUMBER_INPUT_MESSAGE)

    fun getManualCount(): Int = getSingleNumber(COUNT_INPUT_MESSAGE)

    fun lottoInputMessage() = printMessage(LOTTO_INPUT_MESSAGE)

    fun getWinningLottoRepeat(): List<Int> {
        printMessage(ERROR_LOTTO_MESSAGE)
        return getMultipleNumber(null)
    }

    fun getManualLottoRepeat(): List<Int> {
        printMessage(ERROR_LOTTO_MESSAGE)
        return getMultipleNumber(null)
    }

    fun getBonusNumberRepeat(): Int {
        printMessage(ERROR_BONUS_MESSAGE)
        return getSingleNumber(null)
    }

    fun getManualCountRepeat(): Int {
        printMessage(ERROR_COUNT_MESSAGE)
        return getSingleNumber(null)
    }

    fun getManualLotto(): List<Int> = getMultipleNumber(null)

    private fun printMessage(message: String) {
        println(message)
    }

    private fun getSingleNumber(message: String?): Int {
        if (message != null) println(message)
        val input = validateSingleInput(readln())
        return input.toInt()
    }

    private fun getMultipleNumber(message: String?): List<Int> {
        if (message != null) println(message)
        val input = validateMultipleInput(readln())
        return input.split(",").map { it.trim().toInt() }
    }

    private fun validateSingleInput(input: String): String {
        if ((validateEmptyInput(input) && validateNumber(input)) == false) {
            return validateSingleInput(readln())
        }
        return input
    }

    private fun validateMultipleInput(input: String): String {
        if (!validateEmptyInput(input)) return validateMultipleInput(readln())
        input.split(",").forEach { if (!validateNumber(it.trim())) return validateMultipleInput(readln()) }
        return input
    }

    private fun validateEmptyInput(input: String): Boolean {
        if (input.trim().isEmpty()) {
            println("[ERROR] 입력이 존재하지 않습니다.")
            return false
        }
        return true
    }

    private fun validateNumber(input: String): Boolean {
        if (input.toIntOrNull() == null) {
            println("[ERROR] 입력이 정수가 아닙니다: $input")
            return false
        }
        return true
    }
}

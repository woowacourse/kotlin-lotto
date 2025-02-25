package lotto.view

import jdk.internal.joptsimple.internal.Messages.message
import lotto.view.InputMessage.ALERT_INPUT_LOTTO
import lotto.view.InputMessage.BONUS_NUMBER_INPUT_MESSAGE
import lotto.view.InputMessage.COUNT_INPUT_MESSAGE
import lotto.view.InputMessage.MONEY_INPUT_MESSAGE
import lotto.view.InputMessage.WINNING_LOTTO_INPUT_MESSAGE

object InputMessage {
    const val MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    const val WINNING_LOTTO_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    const val BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
    const val COUNT_INPUT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요."
    const val ALERT_INPUT_LOTTO = "수동으로 구매할 번호를 입력해 주세요."
}

class InputView {
    fun getMoney(): Int = getSingleNumber(MONEY_INPUT_MESSAGE)

    fun getWinningLotto(): List<Int> = getMultipleNumber(WINNING_LOTTO_INPUT_MESSAGE)

    fun getBonusNumber(): Int = getSingleNumber(BONUS_NUMBER_INPUT_MESSAGE)

    fun getManualCount(): Int = getSingleNumber(COUNT_INPUT_MESSAGE)

    fun getManualLottoList(count: Int): List<List<Int>> {
        printMessage(ALERT_INPUT_LOTTO)
        return List<List<Int>>(count) { getManualLotto() }
    }

    fun getManualLotto(): List<Int> = getMultipleNumber(null)

    private fun printMessage(message: String) {
        println(message)
    }

    private fun getSingleNumber(message: String): Int {
        println(message)
        val input = readln()
        validateSingleInput(input)
        return input.toInt()
    }

    private fun getMultipleNumber(message: String?): List<Int> {
        if (message != null) println(message)
        val input = readln()
        validateMultipleInput(input)
        return input.split(",").map { it.trim().toInt() }
    }

    private fun validateSingleInput(input: String) {
        validateEmptyInput(input)
        validateNumber(input)
    }

    private fun validateMultipleInput(input: String) {
        validateEmptyInput(input)
        input.split(",").forEach { validateNumber(it.trim()) }
    }

    private fun validateEmptyInput(input: String) {
        require(input.trim().isNotEmpty()) {
            "[ERROR] 입력이 존재하지 않습니다."
        }
    }

    private fun validateNumber(input: String) {
        require(input.toIntOrNull() != null) {
            "[ERROR] 입력이 정수가 아닙니다: $input"
        }
    }
}

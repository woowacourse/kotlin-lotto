package lotto.view

import lotto.view.InputMessage.ALERT_INPUT_LOTTO
import lotto.view.InputMessage.BONUS_NUMBER_INPUT_MESSAGE
import lotto.view.InputMessage.COUNT_INPUT_MESSAGE
import lotto.view.InputMessage.ERROR_MESSAGE
import lotto.view.InputMessage.MONEY_INPUT_MESSAGE
import lotto.view.InputMessage.WINNING_LOTTO_INPUT_MESSAGE

object InputMessage {
    const val MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
    const val WINNING_LOTTO_INPUT_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
    const val BONUS_NUMBER_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
    const val COUNT_INPUT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요."
    const val ALERT_INPUT_LOTTO = "수동으로 구매할 번호를 입력해 주세요."
    const val ERROR_MESSAGE = "[ERROR] 입력이 올바르지 않습니다. 다시 입력해 주세요."
}

class InputView {
    fun getMoney(): Int = getValidSingleNumber(MONEY_INPUT_MESSAGE)

    fun getWinningLotto(): List<Int> = getValidMultipleNumbers(WINNING_LOTTO_INPUT_MESSAGE)

    fun getBonusNumber(): Int = getValidSingleNumber(BONUS_NUMBER_INPUT_MESSAGE)

    fun getManualCount(): Int = getValidSingleNumber(COUNT_INPUT_MESSAGE)

    fun messageManualLotto() = println(ALERT_INPUT_LOTTO)

    fun getManualLotto(): List<Int> = getValidMultipleNumbers(null)

    private fun getValidSingleNumber(message: String): Int {
        while (true) {
            println(message)
            val input = readln().trim()
            val number = input.toIntOrNull()

            if (number != null) return number
            println(ERROR_MESSAGE)
        }
    }

    private fun getValidMultipleNumbers(message: String?): List<Int> {
        while (true) {
            message?.let { println(it) }
            val input = readln().trim()
            val numbers = input.split(",").mapNotNull { it.trim().toIntOrNull() }

            if (numbers.isNotEmpty() && numbers.size == input.split(",").size) return numbers
            println(ERROR_MESSAGE)
        }
    }
}

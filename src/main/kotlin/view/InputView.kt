package view

import domain.lotto.number.LottoNumber
import domain.lotto.size.LottoSize
import domain.money.Money
import util.common.constant.ERROR_PREFIX

class InputView {
    fun inputPurchasingMoney(): Money {
        printMessage(PURCHASING_MONEY_INPUT_MESSAGE)
        return Money(inputNumber())
    }

    fun inputPurchasingManualLottoSize(): LottoSize {
        printEnter()
        println(PURCHASE_MANUAL_LOTTO_COUNT_INPUT_MESSAGE)
        return LottoSize(inputNumber())
    }

    fun inputManualLottoNumbers(size: LottoSize): List<List<LottoNumber>> {
        printEnter()
        printMessage(MANUAL_LOTTO_NUMBERS_INPUT_MESSAGE)
        return List(size.value) { inputManualLottoNumber() }
    }

    private fun inputManualLottoNumber(): List<LottoNumber> =
        inputNumbersBy(WINNING_NUMBER_DELIMITER).map { LottoNumber.of(it) }

    fun inputLastWeekWinningNumbers(): List<LottoNumber> {
        printMessage(LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE)
        return inputNumbersBy(WINNING_NUMBER_DELIMITER).map { LottoNumber.of(it) }
    }

    fun inputBonusBallNumber(): LottoNumber {
        printMessage(BONUS_BALL_NUMBERS_INPUT_MESSAGE)
        return LottoNumber(inputNumber())
    }

    private fun inputNumber(): Int {
        val number = readln().trim().toIntOrNull()
        if (number == null) {
            printMessage(ERROR_MESSAGE_NOT_NUMERIC_INPUT)
            return inputNumber()
        }
        return number
    }

    private fun inputNumbersBy(delimiter: String): List<Int> {
        val numbers = readln().split(delimiter).map { it.trim().toIntOrNull() }
        val isContainsNotNumber = numbers.any { it == null }
        if (isContainsNotNumber) {
            printMessage(ERROR_MESSAGE_CONTAINS_NOT_NUMERIC)
            return inputNumbersBy(delimiter)
        }
        return numbers.map { it!! }
    }

    private fun printMessage(message: String = ""): Unit = println(message)

    private fun printEnter(): Unit = printMessage()

    companion object {
        private const val PURCHASING_MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
        private const val PURCHASE_MANUAL_LOTTO_COUNT_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MANUAL_LOTTO_NUMBERS_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
        private const val LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_BALL_NUMBERS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."

        private const val ERROR_MESSAGE_NOT_NUMERIC_INPUT = "$ERROR_PREFIX 입력값은 숫자여야 합니다."
        private const val ERROR_MESSAGE_CONTAINS_NOT_NUMERIC = "$ERROR_PREFIX 입력에 숫자가 아닌 값이 포함되어 있습니다."

        private const val WINNING_NUMBER_DELIMITER = ","
    }
}

package view

import domain.money.Money
import util.validator.InputValidator

class InputView(
    private val inputValidator: InputValidator
) {
    fun inputPurchasingMoney(): Money {
        println(PURCHASING_MONEY_INPUT_MESSAGE)
        return Money(inputValidator.validateNumeric(readln().trim()))
    }

    fun inputLastWeekWinningNumbers(): List<Int> {
        println(LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE)
        return inputValidator.validateWinningNumbersIsNumeric(readln().split(WINNING_NUMBER_DELIMITER))
    }

    fun inputBonusBallNumber(): Int {
        println(BONUS_BALL_NUMBERS_INPUT_MESSAGE)
        return inputValidator.validateNumeric(readln().trim())
    }

    companion object {
        private const val PURCHASING_MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
        private const val LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_BALL_NUMBERS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."

        private const val WINNING_NUMBER_DELIMITER = ","
    }
}

package view

import util.validator.InputValidator

class InputView(
    private val inputValidator: InputValidator
) {
    fun inputPurchasingMoney(): Int {
        println(PURCHASING_MONEY_INPUT_MESSAGE)
        return readln().trim().toIntOrNull() ?: return inputPurchasingMoney()
    }

    fun inputManualLottoCount(): Int {
        println(PURCHASING_MANUAL_LOTTO_COUNT)
        return readln().trim().toIntOrNull() ?: inputManualLottoCount()
    }

    fun inputManualLottos(manualLottoCount: Int): List<List<Int>> {
        println(PURCHASING_MANUAL_LOTTO)
        val manualLottos = mutableListOf<List<Int>>()
        repeat(manualLottoCount) {
            val manualLotto = inputLottoNumbers() ?: return inputManualLottos(manualLottoCount)
            manualLottos.add(manualLotto)
        }
        return manualLottos
    }

    fun inputLastWeekWinningNumbers(): List<Int> {
        println(LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE)
        return inputLottoNumbers() ?: inputLastWeekWinningNumbers()
    }

    private fun inputLottoNumbers(): List<Int>? {
        return inputValidator.validateWinningNumbersIsNumeric(readln().split(WINNING_NUMBER_DELIMITER))
    }

    fun inputBonusBallNumber(): Int {
        println(BONUS_BALL_NUMBERS_INPUT_MESSAGE)
        return readln().trim().toIntOrNull() ?: inputBonusBallNumber()
    }

    companion object {
        private const val PURCHASING_MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
        private const val PURCHASING_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val PURCHASING_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요."
        private const val LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_BALL_NUMBERS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."

        private const val WINNING_NUMBER_DELIMITER = ","
    }
}

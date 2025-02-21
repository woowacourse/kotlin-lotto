package lotto.view

import java.util.Scanner

class InputView {
    private val scanner = Scanner(System.`in`)

    fun readPurchaseAmount(): Int {
        println(READ_PURCHASE_AMOUNT_MESSAGE)
        return requireNotNull(scanner.nextLine().trim().toIntOrNull()) { INVALID_NUMBER_MESSAGE }
    }

    fun readWinningNumbers(): List<Int> {
        println(READ_WINNING_NUMBERS_MESSAGE)
        val winningNumbersInput = scanner.nextLine().split(WINNING_NUMBERS_DELIMITER).map { it.trim() }
        return winningNumbersInput.map { requireNotNull(it.toIntOrNull()) { INVALID_NUMBER_MESSAGE } }
    }

    fun readBonusNumber(): Int {
        println(READ_BONUS_NUMBER_MESSAGE)
        val bonusNumberInput = scanner.nextLine().trim()
        return requireNotNull(bonusNumberInput.toIntOrNull()) { INVALID_NUMBER_MESSAGE }
    }

    private companion object {
        const val WINNING_NUMBERS_DELIMITER = ','
        const val READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
        const val READ_WINNING_NUMBERS_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
        const val READ_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        const val INVALID_NUMBER_MESSAGE = "숫자만 입력해 주세요.(공백 포함 x)"
    }
}

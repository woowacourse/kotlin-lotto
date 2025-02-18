package lotto.view

import java.util.Scanner

class InputView {
    private val scanner = Scanner(System.`in`)

    fun readPurchaseAmount(): Int {
        println(READ_PURCHASE_AMOUNT_MESSAGE)
        return scanner.nextInt()
    }

    fun readWinningNumbers(): List<Int> {
        println(READ_WINNING_NUMBERS_MESSAGE)
        val winningNumbersInput = scanner.nextLine().split(WINNING_NUMBERS_DELIMITER)
        return winningNumbersInput.map { requireNotNull(it.toIntOrNull()) { INVALID_WINNING_NUMBER_MESSAGE } }
    }

    private companion object {
        const val WINNING_NUMBERS_DELIMITER = ','
        const val READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
        const val READ_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val INVALID_WINNING_NUMBER_MESSAGE = "[ERROR] 숫자만 입력해 주세요"
    }
}

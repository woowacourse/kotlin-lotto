package lotto.view

import lotto.Constants
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket

class InputView {
    fun validateAmount(amount: String): Int {
        require(amount.toIntOrNull() != null) {
            ERROR_INVALID_AMOUNT
        }
        require(amount.toInt() >= Constants.LOTTO_AMOUNT) {
            ERROR_INVALID_MINIMUM_AMOUNT
        }
        return amount.toInt()
    }

    fun validateWinningNumbers(winningNumbers: List<String>): List<Int> {
        require(winningNumbers.all { it.toIntOrNull() != null }) { ERROR_INVALID_WINNING_TYPE }
        require(winningNumbers.size == LOTTO_PICK_COUNT) { ERROR_INVALID_WINNING_COUNT }
        require(winningNumbers.size == winningNumbers.distinct().size) { ERROR_WINNING_DUPLICATE }
        return winningNumbers.map { it.toInt() }
    }

    fun validateBonusNumber(bonusNumber: String): Int {
        require(bonusNumber.toIntOrNull() != null) {
            ERROR_BONUS_TYPE
        }
        return bonusNumber.toInt()
    }

    fun inputPurchaseAmount(): Int {
        println(MESSAGE_INPUT_AMOUNT)
        val purchaseAmount = readln().trim()
        return validateAmount(purchaseAmount)
    }

    fun inputManualCount(): Int {
        println(MESSAGE_INPUT_MANUAL_COUNT)
        val manualCount = readln().trim()
        return manualCount.toInt()
    }

    fun inputManualNumbers(count: Int): List<LottoTicket> {
        println(MESSAGE_INPUT_MANUAL_NUMBERS)
        return List(count) { LottoTicket(readln().split(COMMA).map { LottoNumber(it.trim().toInt()) }.toSet()) }
    }

    fun inputWinningNumbers(): List<Int> {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
        val winningNumbers = readln().split(COMMA).map { it.trim() }
        return validateWinningNumbers(winningNumbers)
    }

    fun inputBonusNumber(): Int {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        val bonusNumber = readln().trim()
        return validateBonusNumber(bonusNumber)
    }

    companion object {
        private const val MESSAGE_INPUT_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val ERROR_INVALID_AMOUNT = "구입금액은 정수를 입력해야 합니다."
        private const val ERROR_INVALID_MINIMUM_AMOUNT = "구입금액은 로또 1장 가격(1000원)보다 커야 합니다."
        private const val ERROR_INVALID_WINNING_TYPE = "당첨번호는 정수를 입력해야 합니다."
        private const val ERROR_INVALID_WINNING_COUNT = "당첨 번호는 6개여야 합니다."
        private const val ERROR_WINNING_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다."
        private const val ERROR_BONUS_TYPE = "보너스 번호는 정수를 입력해야 합니다."

        private const val COMMA = ","
        private const val LOTTO_PICK_COUNT = 6
    }
}

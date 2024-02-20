package util

import model.Winning

object InputValidator {
    private const val INPUT_SEPARATOR = ','

    fun validatePurchaseAmount(input: String): Int {
        val purchaseAmount = input.toIntOrNull() ?: 0

        validatePurchaseAmountRange(purchaseAmount)
        validatePurchaseAmountUnit(purchaseAmount)

        return purchaseAmount
    }

    fun validateWinningNumbers(input: String): List<Int> {
        val winningNumbers = input.split(INPUT_SEPARATOR).map { winningNumber ->
            validateWinningNumber(winningNumber)
        }
        validateWinningNumbersSize(winningNumbers)
        validateWinningNumbersDuplicate(winningNumbers)
        return winningNumbers
    }

    private fun validateWinningNumber(winningNumber: String): Int {
        val validWinningNumber = winningNumber.toIntOrNull() ?: 0
        validateWinningNumberRange(validWinningNumber)
        return validWinningNumber
    }

    private fun validateWinningNumberRange(winningNumber: Int) {
        require(winningNumber in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE) { InputException.INVALID_WINNING_NUMBER_RANGE.getMessage() }
    }

    private fun validateWinningNumbersSize(winningNumbers: List<Int>) {
        require(winningNumbers.size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_SIZE.getMessage() }
    }

    private fun validateWinningNumbersDuplicate(winningNumbers: List<Int>) {
        require(winningNumbers.toSet().size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_DUPLICATE.getMessage() }
    }

    private fun validatePurchaseAmountRange(purchaseAmount: Int) {
        require(purchaseAmount >= Constant.PURCHASE_AMOUNT_UNIT) { InputException.INVALID_PURCHASE_AMOUNT_RANGE.getMessage() }
    }

    private fun validatePurchaseAmountUnit(purchaseAmount: Int) {
        require(purchaseAmount % Constant.PURCHASE_AMOUNT_UNIT == 0) { InputException.INVALID_PURCHASE_AMOUNT_UNIT.getMessage() }
    }

    fun validateBonusNumber(input: String, winning: Winning): Int {
        val bonusNumber = input.toIntOrNull() ?: 0
        validateBonusNumberRange(bonusNumber)
        validateBonusNumberDuplicate(bonusNumber, winning)
        return bonusNumber
    }

    private fun validateBonusNumberRange(bonusNumber: Int) {
        require(bonusNumber in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE) { InputException.INVALID_BONUS_NUMBER_RANGE.getMessage() }
    }

    private fun validateBonusNumberDuplicate(bonusNumber: Int, winning: Winning) {
        require(!winning.numbers.contains(bonusNumber)) { InputException.INVALID_BONUS_NUMBER_DUPLICATE.getMessage() }
    }
}

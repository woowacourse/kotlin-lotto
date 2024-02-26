package util

import model.LottoNumber

object InputValidator {
    private const val INPUT_SEPARATOR = ", "

    fun validatePurchaseAmount(input: String): Int {
        val purchaseAmount = input.toIntOrNull() ?: 0

        validatePurchaseAmountRange(purchaseAmount)
        validatePurchaseAmountUnit(purchaseAmount)

        return purchaseAmount
    }

    fun validateHandpickedNumber(input: String): Int {
        val handpickedNumber = input.toIntOrNull() ?: 0

        validateHandpickedNumberRange(handpickedNumber)

        return handpickedNumber
    }

    fun validateHandpickedNumbers(input: String): List<LottoNumber> {
        val handpickedNumbers =
            input.split(INPUT_SEPARATOR).map { handpickedNumber ->
                validateNumber(handpickedNumber)
            }

        validateNumbersSize(handpickedNumbers)
        validateNumbersDuplicate(handpickedNumbers)
        return handpickedNumbers
    }

    fun validateWinningNumbers(input: String): List<LottoNumber> {
        val winningNumbers =
            input.split(INPUT_SEPARATOR).map { winningNumber ->
                validateNumber(winningNumber)
            }

        validateNumbersSize(winningNumbers)
        validateNumbersDuplicate(winningNumbers)
        return winningNumbers
    }

    fun validateBonusNumber(
        input: String,
        winningNumbers: List<LottoNumber>,
    ): LottoNumber {
        val bonusNumber = LottoNumber(input.toIntOrNull() ?: 0)
        validateBonusNumberRange(bonusNumber)
        validateBonusNumberDuplicate(bonusNumber, winningNumbers)
        return bonusNumber
    }

    private fun validatePurchaseAmountRange(purchaseAmount: Int) {
        require(purchaseAmount >= Constant.PURCHASE_AMOUNT_UNIT) { InputException.INVALID_PURCHASE_AMOUNT_RANGE.getMessage() }
    }

    private fun validatePurchaseAmountUnit(purchaseAmount: Int) {
        require(purchaseAmount % Constant.PURCHASE_AMOUNT_UNIT == 0) { InputException.INVALID_PURCHASE_AMOUNT_UNIT.getMessage() }
    }

    private fun validateHandpickedNumberRange(handpickedNumber: Int) {
        require(handpickedNumber >= 0) { InputException.INVALID_HANDPICKED_NUMBER_RANGE.getMessage() }
    }

    private fun validateNumber(number: String): LottoNumber {
        val validNumber = LottoNumber(number.toIntOrNull() ?: 0)
        validateWinningNumberRange(validNumber)
        return validNumber
    }

    private fun validateWinningNumberRange(winningNumber: LottoNumber) {
        require(winningNumber.getNumber() in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE) {
            InputException.INVALID_WINNING_NUMBER_RANGE.getMessage()
        }
    }

    private fun validateNumbersSize(winningNumbers: List<LottoNumber>) {
        require(winningNumbers.size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_SIZE.getMessage() }
    }

    private fun validateNumbersDuplicate(winningNumbers: List<LottoNumber>) {
        val numbers = winningNumbers.map { it.getNumber() }
        require(numbers.toSet().size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_DUPLICATE.getMessage() }
    }

    private fun validateBonusNumberRange(bonusNumber: LottoNumber) {
        require(bonusNumber.getNumber() in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE) {
            InputException.INVALID_BONUS_NUMBER_RANGE.getMessage()
        }
    }

    private fun validateBonusNumberDuplicate(
        bonusNumber: LottoNumber,
        winningNumbers: List<LottoNumber>,
    ) {
        val numbers = winningNumbers.map { it.getNumber() }
        require(!numbers.contains(bonusNumber.getNumber())) {
            InputException.INVALID_BONUS_NUMBER_DUPLICATE.getMessage()
        }
    }
}

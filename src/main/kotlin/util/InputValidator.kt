package util

object InputValidator {
    private const val INPUT_SEPARATOR = ','

    fun validatePurchaseAmount(input: String): Int {
        val purchaseAmount = input.toIntOrNull() ?: 0

        validatePurchaseAmountRange(purchaseAmount)
        validatePurchaseAmountUnit(purchaseAmount)

        return purchaseAmount
    }

    fun validatePurchaseSizeOfManualLotto(
        input: String,
        purchaseAmount: Int,
    ): Int {
        val purchaseSizeOfManualLotto = input.toIntOrNull() ?: -1

        validateMinimumPurchaseSizeOfManualLotto(purchaseSizeOfManualLotto)
        validatePurchaseSizeOfManualLottoExceedPurchaseAmount(purchaseSizeOfManualLotto, purchaseAmount)

        return purchaseSizeOfManualLotto
    }

    private fun validateMinimumPurchaseSizeOfManualLotto(purchaseSizeOfManualLotto: Int) {
        require(purchaseSizeOfManualLotto >= Constant.MINIMUM_PURCHASE_SIZE_OF_MANUAL_LOTTO) {
            InputException.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO.getMessage()
        }
    }

    private fun validatePurchaseSizeOfManualLottoExceedPurchaseAmount(
        purchaseSizeOfManualLotto: Int,
        purchaseAmount: Int,
    ) {
        require(purchaseSizeOfManualLotto * Constant.PURCHASE_AMOUNT_UNIT <= purchaseAmount) {
            InputException.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO_AMOUNT.getMessage()
        }
    }

    fun validatePublishedLottos(input: String): List<Int> {
        val numbers =
            input.split(INPUT_SEPARATOR).map { number ->
                validatePublishedLotto(number)
            }
        validateNumbersSize(numbers)
        validateWinningNumbersDuplicate(numbers)
        return numbers
    }

    private fun validatePublishedLotto(number: String): Int {
        val validNumber = number.toIntOrNull() ?: 0
        validateNumberRange(validNumber)
        return validNumber
    }

    private fun validateNumberRange(number: Int) {
        require(
            number in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE,
        ) { InputException.INVALID_WINNING_NUMBER_RANGE.getMessage() }
    }

    private fun validateNumbersSize(numbers: List<Int>) {
        require(numbers.size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_SIZE.getMessage() }
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

    fun validateBonusNumber(
        input: String,
        winningNumbers: List<Int>,
    ): Int {
        val bonusNumber = input.toIntOrNull() ?: 0
        validateBonusNumberRange(bonusNumber)
        validateBonusNumberDuplicate(bonusNumber, winningNumbers)
        return bonusNumber
    }

    private fun validateBonusNumberRange(bonusNumber: Int) {
        require(
            bonusNumber in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE,
        ) { InputException.INVALID_BONUS_NUMBER_RANGE.getMessage() }
    }

    private fun validateBonusNumberDuplicate(
        bonusNumber: Int,
        winningNumbers: List<Int>,
    ) {
        require(!winningNumbers.contains(bonusNumber)) { InputException.INVALID_BONUS_NUMBER_DUPLICATE.getMessage() }
    }
}

package util

import model.LottoNumber

object InputValidator {
    private const val INPUT_SEPARATOR = ','
    private const val INVALID_INPUT = -1

    fun validatePurchaseAmount(input: String): Int {
        val purchaseAmount = input.toIntOrNull() ?: INVALID_INPUT

        validatePurchaseAmountRange(purchaseAmount)
        validatePurchaseAmountUnit(purchaseAmount)

        return purchaseAmount
    }

    fun validatePurchaseSizeOfManualLotto(
        input: String,
        purchaseAmount: Int,
    ): Int {
        val purchaseSizeOfManualLotto = input.toIntOrNull() ?: INVALID_INPUT

        validateMinimumPurchaseSizeOfManualLotto(purchaseSizeOfManualLotto)
        validatePurchaseSizeOfManualLottoExceedPurchaseAmount(purchaseSizeOfManualLotto, purchaseAmount)

        return purchaseSizeOfManualLotto
    }

    private fun validateMinimumPurchaseSizeOfManualLotto(purchaseSizeOfManualLotto: Int) {
        require(purchaseSizeOfManualLotto > LottoConstants.MINIMUM_PURCHASE_SIZE_OF_MANUAL_LOTTO) {
            InputException.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO.getMessage()
        }
    }

    private fun validatePurchaseSizeOfManualLottoExceedPurchaseAmount(
        purchaseSizeOfManualLotto: Int,
        purchaseAmount: Int,
    ) {
        require(purchaseSizeOfManualLotto * LottoConstants.UNIT_PRICE <= purchaseAmount) {
            InputException.INVALID_PURCHASE_SIZE_OF_MANUAL_LOTTO_AMOUNT.getMessage()
        }
    }

    fun validatePublishedLottos(input: String): List<LottoNumber> {
        val numbers =
            input.split(INPUT_SEPARATOR).map { number ->
                validatePublishedLotto(number)
            }
        validateNumbersSize(numbers)
        validateWinningNumbersDuplicate(numbers)
        return numbers
    }

    private fun validatePublishedLotto(number: String): LottoNumber {
        val validNumber = number.toIntOrNull() ?: INVALID_INPUT
        validateNumberRange(validNumber)
        return LottoNumber(validNumber)
    }

    private fun validateNumberRange(number: Int) {
        require(
            number in LottoConstants.START_RANGE..LottoConstants.END_RANGE,
        ) { InputException.INVALID_WINNING_NUMBER_RANGE.getMessage() }
    }

    private fun validateNumbersSize(numbers: List<LottoNumber>) {
        require(numbers.size == LottoConstants.SIZE) { InputException.INVALID_WINNING_NUMBERS_SIZE.getMessage() }
    }

    private fun validateWinningNumbersDuplicate(winningNumbers: List<LottoNumber>) {
        require(winningNumbers.toSet().size == LottoConstants.SIZE) { InputException.INVALID_WINNING_NUMBERS_DUPLICATE.getMessage() }
    }

    private fun validatePurchaseAmountRange(purchaseAmount: Int) {
        require(purchaseAmount >= LottoConstants.UNIT_PRICE) { InputException.INVALID_PURCHASE_AMOUNT_RANGE.getMessage() }
    }

    private fun validatePurchaseAmountUnit(purchaseAmount: Int) {
        require(purchaseAmount % LottoConstants.UNIT_PRICE == 0) { InputException.INVALID_PURCHASE_AMOUNT_UNIT.getMessage() }
    }

    fun validateBonusNumber(
        input: String,
        winningNumbers: List<LottoNumber>,
    ): Int {
        val bonusNumber = input.toIntOrNull() ?: INVALID_INPUT
        validateBonusNumberRange(bonusNumber)
        validateBonusNumberDuplicate(bonusNumber, winningNumbers)
        return bonusNumber
    }

    private fun validateBonusNumberRange(bonusNumber: Int) {
        require(
            bonusNumber in LottoConstants.START_RANGE..LottoConstants.END_RANGE,
        ) { InputException.INVALID_BONUS_NUMBER_RANGE.getMessage() }
    }

    private fun validateBonusNumberDuplicate(
        bonusNumber: Int,
        winningNumbers: List<LottoNumber>,
    ) {
        require(!winningNumbers.map { it.number }.contains(bonusNumber)) {
            InputException.INVALID_BONUS_NUMBER_DUPLICATE.getMessage()
        }
    }
}

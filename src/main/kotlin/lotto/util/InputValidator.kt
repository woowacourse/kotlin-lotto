package lotto.util

object InputValidator {
    private const val INPUT_SEPARATOR = ','
    private const val NOT_INT_INPUT = 0
    private const val NOT_INT_INPUT_FOR_NUMBER_OF_HANDPICKED_LOTTO = -1
    private const val MIN_HANDPICKED_LOTTO_RANGE = 0

    fun validatePurchaseAmount(input: String): Int {
        val purchaseAmount = input.toIntOrNull() ?: NOT_INT_INPUT
        validatePurchaseAmountRange(purchaseAmount)
        return purchaseAmount
    }

    fun validateNumberOfHandpickedLotto(
        input: String,
        numberOfLotto: Int,
    ): Int {
        val numberOfHandpickedLotto = input.toIntOrNull() ?: NOT_INT_INPUT_FOR_NUMBER_OF_HANDPICKED_LOTTO
        validateNumberOfHandpickedNumberLottoRange(numberOfHandpickedLotto, numberOfLotto)
        return numberOfHandpickedLotto
    }

    fun validateHandpickedNumbers(input: String): List<Int> {
        val handpickedNumbers =
            input.split(INPUT_SEPARATOR).map { handpickedNumber ->
                validateNumber(handpickedNumber)
            }
        validateNumbersSize(handpickedNumbers)
        validateNumbersDuplicate(handpickedNumbers)
        return handpickedNumbers
    }

    fun validateWinningNumbers(input: String): List<Int> {
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
        winningNumbers: List<Int>,
    ): Int {
        val bonusNumber = input.toIntOrNull() ?: NOT_INT_INPUT
        validateBonusNumberRange(bonusNumber)
        validateBonusNumberDuplicate(bonusNumber, winningNumbers)
        return bonusNumber
    }

    private fun validatePurchaseAmountRange(purchaseAmount: Int) {
        require(purchaseAmount >= Constant.PURCHASE_AMOUNT_UNIT) { InputException.INVALID_PURCHASE_AMOUNT_RANGE.getMessage() }
    }

    private fun validateNumberOfHandpickedNumberLottoRange(
        handpickedNumber: Int,
        numberOfLotto: Int,
    ) {
        require(handpickedNumber in MIN_HANDPICKED_LOTTO_RANGE..numberOfLotto) {
            InputException.INVALID_HANDPICKED_NUMBER_RANGE.getMessage()
        }
    }

    private fun validateNumber(number: String): Int {
        val validNumber = number.trim().toIntOrNull() ?: NOT_INT_INPUT
        validateWinningNumberRange(validNumber)
        return validNumber
    }

    private fun validateWinningNumberRange(winningNumber: Int) {
        require(winningNumber in Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE) {
            InputException.INVALID_WINNING_NUMBER_RANGE.getMessage()
        }
    }

    private fun validateNumbersSize(winningNumbers: List<Int>) {
        require(winningNumbers.size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_SIZE.getMessage() }
    }

    private fun validateNumbersDuplicate(winningNumbers: List<Int>) {
        require(winningNumbers.toSet().size == Constant.LOTTO_SIZE) { InputException.INVALID_WINNING_NUMBERS_DUPLICATE.getMessage() }
    }

    private fun validateBonusNumberRange(bonusNumber: Int) {
        require(bonusNumber in Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE) {
            InputException.INVALID_BONUS_NUMBER_RANGE.getMessage()
        }
    }

    private fun validateBonusNumberDuplicate(
        bonusNumber: Int,
        winningNumbers: List<Int>,
    ) {
        require(!winningNumbers.contains(bonusNumber)) {
            InputException.INVALID_BONUS_NUMBER_DUPLICATE.getMessage()
        }
    }
}

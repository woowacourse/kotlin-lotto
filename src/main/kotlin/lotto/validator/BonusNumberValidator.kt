package lotto.validator

import lotto.constant.ErrorConstants

class BonusNumberValidator(input: String, winningNumber: List<Int>) {
    init {
        require(input.toIntOrNull() != null) { ErrorConstants.ERROR_NOT_INTEGER }
        require(!winningNumber.contains(input.toInt())) { ErrorConstants.ERROR_NOT_DUPLICATE_NUMBER }
        require(input.toInt() in MIN_BOUND..MAX_BOUND) { ErrorConstants.ERROR_OUT_OF_RANGE }
    }

    companion object {
        const val MIN_BOUND = 1
        const val MAX_BOUND = 45
    }
}

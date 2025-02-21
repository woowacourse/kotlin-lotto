package lotto.validator

import lotto.constant.ErrorConstants

class WinningNumberValidator(input: List<String>) {
    init {
        require(input.size == MAX_LOTTO_SIZE) { ErrorConstants.ERROR_NOT_SIX }
        require(input.toSet().size == input.size) { ErrorConstants.ERROR_NOT_DUPLICATE_NUMBER }
        require(!input.map { it.toIntOrNull() }.contains(null)) { ErrorConstants.ERROR_NOT_INTEGER }
    }

    companion object {
        const val MAX_LOTTO_SIZE = 6
        const val MIN_BOUND = 1
        const val MAX_BOUND = 45
    }
}

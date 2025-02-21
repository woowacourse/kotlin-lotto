package lotto.validator

import lotto.constant.ErrorConstants

class WinningNumberValidator(input: List<String>) {
    init {
        require(!input.map { it.toIntOrNull() }.contains(null)) { ErrorConstants.ERROR_NOT_INTEGER }
    }
}

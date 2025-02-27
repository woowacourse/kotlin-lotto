package lotto.view

import lotto.contants.LottoRuleConstants

class InputValidator {
    fun validateInteger(input: String) = require(input.toIntOrNull() != null) { ERROR_INVALID_INTEGER }

    fun validateOverZero(input: String) = require(input.toInt() > LottoRuleConstants.ZERO.value) { ERROR_INVALID_OVER_ZERO }

    companion object {
        private const val ERROR_INVALID_INTEGER = "정수를 입력해야 합니다."
        private const val ERROR_INVALID_OVER_ZERO = "구입금액은 0보다 커야 합니다."
    }
}

package lotto.view

import lotto.contants.LottoRuleConstants

class InputValidator {
    fun validateInteger(input: String) = require(input.toIntOrNull() != null) { ERROR_INVALID_INTEGER }

    fun validateOverZero(input: String) = require(input.toInt() > LottoRuleConstants.ZERO.value) { ERROR_INVALID_OVER_ZERO }

    fun validateAmountUnits1000(input: String) = require(input.toInt() % LottoRuleConstants.LOTTO_AMOUNT.value == LottoRuleConstants.ZERO.value) { ERROR_INVALID_DIVISION }

    companion object {
        private const val ERROR_INVALID_INTEGER = "정수를 입력해야 합니다."
        private const val ERROR_INVALID_OVER_ZERO = "구입금액은 0보다 커야 합니다."
        private const val ERROR_INVALID_DIVISION = "구입금액은 1000의 배수여야 합니다."
    }
}

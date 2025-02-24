package lotto.view

import lotto.contants.LottoRuleConstants

class InputValidator {
    fun validateAmount(amount: String) {
        require(amount.toIntOrNull() != null) {
            IllegalArgumentException(ERROR_INVALID_AMOUNT)
        }
        require(amount.toInt() > LottoRuleConstants.ZERO) {
            IllegalArgumentException(ERROR_INVALID_OVER_ZERO)
        }
        require(amount.toInt() % LottoRuleConstants.LOTTO_AMOUNT == LottoRuleConstants.ZERO) {
            IllegalArgumentException(ERROR_INVALID_DIVISION)
        }
    }

    fun validateWinningNumbers(winningNumbers: List<String>) {
        require(winningNumbers.all { it.toIntOrNull() != null }) {
            IllegalArgumentException(ERROR_INVALID_WINNING_TYPE)
        }
    }

    fun validateBonusNumber(bonusNumber: String) {
        require(bonusNumber.toIntOrNull() != null) {
            IllegalArgumentException(ERROR_INVALID_WINNING_TYPE)
        }
    }

    companion object {
        private const val ERROR_INVALID_AMOUNT = "구입금액은 정수를 입력해야 합니다."
        private const val ERROR_INVALID_OVER_ZERO = "구입금액은 0보다 커야 합니다."
        private const val ERROR_INVALID_DIVISION = "구입금액은 1000의 배수여야 합니다."
        private const val ERROR_INVALID_WINNING_TYPE = "당첨번호는 정수를 입력해야 합니다."
    }
}

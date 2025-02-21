package lotto.validator

import lotto.constant.ErrorConstants
import lotto.domain.Lotto
import lotto.domain.LottoNumber

class BonusNumberValidator(input: String, winningNumber: Lotto) {
    init {
        require(input.toIntOrNull() != null) { ErrorConstants.ERROR_NOT_INTEGER }
        require(!winningNumber.numbers.contains(LottoNumber(input.toInt()))) { ERROR_DUPLICATE_BONUS_NUMBER }
    }

    companion object {
        private const val ERROR_DUPLICATE_BONUS_NUMBER: String = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}

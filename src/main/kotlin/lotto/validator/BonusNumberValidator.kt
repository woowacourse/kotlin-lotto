package lotto.validator

import lotto.constant.ErrorConstants
import lotto.domain.Lotto
import lotto.domain.LottoNumber

class BonusNumberValidator(input: String, winningNumber: Lotto) {
    init {
        require(input.toIntOrNull() != null) { ErrorConstants.ERROR_NOT_INTEGER }
        require(!winningNumber.numbers.contains(LottoNumber(input.toInt()))) { ErrorConstants.ERROR_NOT_DUPLICATE_NUMBER }
    }
}

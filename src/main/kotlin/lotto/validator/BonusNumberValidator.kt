package lotto.validator

import lotto.constant.ErrorConstants
import lotto.domain.LottoNumber

class BonusNumberValidator(input: String, winningNumber: List<LottoNumber>) {
    init {
        require(input.toIntOrNull() != null) { ErrorConstants.ERROR_NOT_INTEGER }
    }
}

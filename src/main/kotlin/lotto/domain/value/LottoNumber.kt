package lotto.domain.value

import lotto.constants.ErrorMessages
import lotto.constants.LottoConstants

@JvmInline
value class LottoNumber(
    val number: Int,
) {
    init {
        require(
            number in LottoConstants.MINIMUM_LOTTO_NUMBER..LottoConstants.MAXIMUM_LOTTO_NUMBER,
        ) { ErrorMessages.INVALID_LOTTO_NUMBER_RANGE }
    }
}

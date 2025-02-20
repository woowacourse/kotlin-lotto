package lotto.domain.value

import lotto.constants.LottoConstants

@JvmInline
value class LottoNumber(
    val number: Int,
) {
    init {
        require(number in LottoConstants.LOTTO_RANGE)
    }
}

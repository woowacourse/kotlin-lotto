package lotto.model

import lotto.constants.LottoConstants.LOTTO_MAX_NUMBER
import lotto.constants.LottoConstants.LOTTO_MIN_NUMBER
import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER_RANGE

data class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) {
            INVALID_LOTTO_NUMBER_RANGE.format(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
        }
    }

    override fun toString() = number.toString()
}

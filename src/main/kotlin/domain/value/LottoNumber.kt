package domain.value

import constants.LottoConstants

class LottoNumber(val number: Int) {
    init {
        require(number in LottoConstants.LOTTO_RANGE)
    }
}

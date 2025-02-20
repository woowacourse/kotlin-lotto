package lotto.domain.value

import lotto.constants.LottoConstants

class LottoNumber(
    val number: Int,
) {
    init {
        require(number in LottoConstants.LOTTO_RANGE) { NOT_IN_LOTTO_RANGE_NUMBER_EXITS }
    }

    companion object {
        private val NOT_IN_LOTTO_RANGE_NUMBER_EXITS = "${LottoConstants.LOTTO_RANGE} 범위 내의 숫자로 입력해주세요."
    }
}

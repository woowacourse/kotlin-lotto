package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class LottoNumber(private val number: Int) {
    init {
        require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { ERROR_LOTTO_RANGE }
    }

    companion object {
        private const val ERROR_LOTTO_RANGE = "로또의 숫자는 ${MINIMUM_LOTTO_RANGE}에서 ${MAXIMUM_LOTTO_RANGE}사이의 숫자여야 합니다."
    }
}

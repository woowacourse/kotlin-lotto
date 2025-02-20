package lotto.model

import lotto.Constants

data class LottoNumber(
    val number: Int,
) {
    init {
        require(number in Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER) {
            IllegalArgumentException(ERROR_INVALID_RANGE)
        }
    }

    companion object {
        private const val ERROR_INVALID_RANGE = "숫자는 1에서 45 사이여야 합니다."
    }
}

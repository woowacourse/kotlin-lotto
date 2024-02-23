package lotto.model

import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER_RANGE

data class LottoNumber(val number: Int) {
    init {
        require(number in MIN_RANGE..MAX_RANGE) {
            INVALID_LOTTO_NUMBER_RANGE.format(MIN_RANGE, MAX_RANGE)
        }
    }

    override fun toString() = number.toString()

    companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
    }
}

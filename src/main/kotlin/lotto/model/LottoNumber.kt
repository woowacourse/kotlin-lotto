package lotto.model

import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER_RANGE

data class LottoNumber(val number: Int) {
    init {
        require(number in MIN_RANGE_NUM..MAX_RANGE_NUM) {
            INVALID_LOTTO_NUMBER_RANGE.format(MIN_RANGE_NUM, MAX_RANGE_NUM)
        }
    }

    override fun toString() = number.toString()

    companion object {
        private const val MIN_RANGE_NUM = 1
        private const val MAX_RANGE_NUM = 45
    }
}

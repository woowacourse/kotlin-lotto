package model

import util.Constant

class LottoNumber(private val number: Int) {
    init {
        require(number in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE)
    }

    fun getNumber(): Int = number
}

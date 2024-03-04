package lotto.model

import lotto.util.Constant

class LottoNumber(private val number: Int) {
    init {
        require(number in Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE)
    }

    fun getNumber(): Int = number

    override fun toString(): String {
        return number.toString()
    }
}

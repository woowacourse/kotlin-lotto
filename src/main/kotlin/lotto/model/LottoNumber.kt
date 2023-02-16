package lotto.model

import lotto.view.ERROR_OUT_OF_RANGE

class LottoNumber(val number: Int): Comparable<LottoNumber> {

    init {
        require(number in 1..45) { ERROR_OUT_OF_RANGE }
    }

    override fun compareTo(other: LottoNumber): Int {
        return number.compareTo(other.number)
    }

    override fun equals(other: Any?): Boolean {
        return number == (other as LottoNumber).number
    }
}

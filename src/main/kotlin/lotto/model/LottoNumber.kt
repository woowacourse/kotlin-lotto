package lotto.model

import lotto.view.ERROR_OUT_OF_RANGE

class LottoNumber(val number: Int) : Comparable<LottoNumber> {

    init {
        require(number in START_LOTTO_RANGE..END_LOTTO_RANGE) { ERROR_OUT_OF_RANGE }
    }

    override fun compareTo(other: LottoNumber): Int {
        return number.compareTo(other.number)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LottoNumber

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }

    companion object {
        const val START_LOTTO_RANGE = 1
        const val END_LOTTO_RANGE = 45
    }
}

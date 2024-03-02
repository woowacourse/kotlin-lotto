package model

import util.LottoConstants

data class LottoNumber(val number: Int) {
    companion object {
        fun from(number: String): LottoNumber {
            require(number.toIntOrNull() != null)
            require(number.toIntOrNull() in LottoConstants.START_RANGE..LottoConstants.END_RANGE)
            return LottoNumber(number.toInt())
        }
    }
}

package lotto.model

import lotto.exception.ErrorCode
import lotto.exception.ExceptionsHandler

data class LottoNumber(val number: Int) {
    override fun toString(): String = number.toString()

    companion object {
        val LOTTO_NUMBER_RANGE: IntRange = 1..45

        fun from(strNumber: String): LottoNumber {
            ExceptionsHandler.handleValidation(ErrorCode.INVALID_NUMBER_EXCEPTION) { strNumber.toIntOrNull() != null }
            ExceptionsHandler.handleValidation(ErrorCode.LOTTO_NUMBER_OUT_OF_RANGE) { strNumber.toIntOrNull() in LOTTO_NUMBER_RANGE }
            return LottoNumber(strNumber.toInt())
        }
    }
}

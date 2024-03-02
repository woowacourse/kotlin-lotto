package model

import exception.ErrorCode
import exception.ExceptionHandler.handleException
import util.LottoConstants

data class LottoNumber(val number: Int) {
    companion object {
        fun from(number: String): LottoNumber {
            handleException(ErrorCode.INVALID_LOTTO_NUMBER) { number.toIntOrNull() != null }
            handleException(ErrorCode.INVALID_LOTTO_NUMBER_RANGE) {
                number.toIntOrNull() in LottoConstants.START_RANGE..LottoConstants.END_RANGE
            }
            return LottoNumber(number.toInt())
        }
    }
}

package model

import exception.ErrorCode
import exception.ExceptionHandler.handleException
import util.LottoConstants

private const val DEFAULT_SEPARATOR = ", "
private const val START = "["
private const val END = "]"

class Lotto(val numbers: List<LottoNumber>) {
    init {
        handleException(ErrorCode.INVALID_WINNING_NUMBERS_SIZE) { numbers.toSet().size == LottoConstants.SIZE }
    }

    override fun toString(): String {
        return numbers.map { it.number }.sorted().joinToString(DEFAULT_SEPARATOR, START, END)
    }
}

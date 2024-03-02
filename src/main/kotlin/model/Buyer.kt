package model

import exception.ErrorCode
import exception.ExceptionHandler.handleException
import util.LottoConstants

class Buyer private constructor(val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / LottoConstants.UNIT_PRICE
    }

    companion object {
        fun from(purchaseAmount: String): Buyer {
            handleException(ErrorCode.INVALID_PURCHASE_AMOUNT_RANGE) { purchaseAmount.toIntOrNull() != null }
            handleException(ErrorCode.INVALID_PURCHASE_AMOUNT_RANGE) { purchaseAmount.toInt() >= LottoConstants.UNIT_PRICE }
            handleException(ErrorCode.INVALID_PURCHASE_AMOUNT_UNIT) { purchaseAmount.toInt() / LottoConstants.UNIT_PRICE != 0 }

            return Buyer(purchaseAmount.toInt())
        }
    }
}

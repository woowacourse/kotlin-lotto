package lotto.model

import lotto.exception.ErrorCode
import lotto.exception.ExceptionHandler.handleException
import lotto.util.LottoConstants

class Money private constructor(val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / LottoConstants.UNIT_PRICE
    }

    fun calculateAutoLottoCount(manualLottoCount: Int): Int {
        return numberOfLotto - manualLottoCount
    }

    companion object {
        fun from(purchaseAmount: String): Money {
            handleException(ErrorCode.INVALID_PURCHASE_AMOUNT_RANGE) { purchaseAmount.toIntOrNull() != null }
            handleException(ErrorCode.INVALID_PURCHASE_AMOUNT_RANGE) { purchaseAmount.toInt() >= LottoConstants.UNIT_PRICE }
            handleException(ErrorCode.INVALID_PURCHASE_AMOUNT_UNIT) { purchaseAmount.toInt() / LottoConstants.UNIT_PRICE != 0 }

            return Money(purchaseAmount.toInt())
        }
    }
}

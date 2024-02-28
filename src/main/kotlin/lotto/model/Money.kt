package lotto.model

import lotto.exception.ErrorCode.PURCHASE_AMOUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler.handleValidation

data class Money(val value: Int) {
    companion object {
        fun from(strPrice: String): Money {
            handleValidation(PURCHASE_AMOUNT_NOT_NATURAL_NUMBER) { strPrice.toIntOrNull() != null }
            return Money(strPrice.toInt())
        }
    }
}

package model

import util.LottoConstants
import util.Validator

class Buyer private constructor(val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / LottoConstants.UNIT_PRICE
    }

    companion object {
        fun from(purchaseAmount: String): Buyer {
            Validator.validatePurchaseAmount(purchaseAmount)
            return Buyer(purchaseAmount.toInt())
        }
    }
}

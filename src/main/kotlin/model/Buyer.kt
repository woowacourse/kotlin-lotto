package model

import util.LottoConstants

class Buyer(private val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / LottoConstants.UNIT_PRICE
    }
}

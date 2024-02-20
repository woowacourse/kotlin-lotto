package model

import util.Constant

class Buyer(private val purchaseAmount: Int) {
    val numberOfLotto = calculateNumberOfLotto()

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / Constant.PURCHASE_AMOUNT_UNIT
    }
}
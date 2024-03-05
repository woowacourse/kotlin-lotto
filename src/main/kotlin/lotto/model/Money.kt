package lotto.model

import lotto.exception.Exceptions

private const val LOTTO_UNIT_PRICE = 1_000

class Money private constructor(val purchaseAmount: Int) {
    val numberOfLotto: Int = calculateNumberOfLotto()

    fun calculateNumberOfLotto(): Int {
        return purchaseAmount / LOTTO_UNIT_PRICE
    }

    fun calculateAutoLottoCount(manualLottoCount: Int): Int {
        return numberOfLotto - manualLottoCount
    }

    companion object {
        fun from(purchaseAmount: String): Money {
            if (purchaseAmount.toInt() < LOTTO_UNIT_PRICE) {
                throw Exceptions.PurchaseAmountRangeException()
            }
            return Money(purchaseAmount.toInt())
        }
    }
}

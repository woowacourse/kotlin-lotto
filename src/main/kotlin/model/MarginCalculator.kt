package model

class MarginCalculator {

    fun calculate(winningPrize: Money, purchaseAmount: Money): Double =
        winningPrize.amount * 1000 / purchaseAmount.amount / 1000.0
}

package lotto.domain

import lotto.domain.model.Margin
import lotto.domain.model.Money

object MarginCalculator {

    fun calculateMarginRate(winningPrize: Money, purchaseAmount: Money): Margin =
        Margin((winningPrize.amount / purchaseAmount.amount.toDouble()))
}

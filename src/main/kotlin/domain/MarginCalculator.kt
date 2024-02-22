package domain

import domain.model.LottoDrawingResult
import domain.model.Margin
import domain.model.Money
import util.Constants.LOTTO_PRICE

object MarginCalculator {

    fun calculateTotalPrize(result: LottoDrawingResult): Money {
        return Money(
            result.statistics.entries.sumOf { (rank, count) ->
                rank.winningMoney * count.toLong()
            }
        )
    }

    fun calculateMarginRate(winningPrize: Money, purchaseAmount: Money): Margin =
        Margin(winningPrize.amount * LOTTO_PRICE / purchaseAmount.amount / LOTTO_PRICE.toDouble())
}

package lotto.domain

import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.Margin
import lotto.domain.model.Money
import lotto.util.Constants.LOTTO_PRICE

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

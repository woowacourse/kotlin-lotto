package model

object MarginCalculator {

    fun calculateTotalPrize(result: LottoDrawingResult): Money {
        return Money(
            result.statistics.entries.sumOf { (rank, count) ->
                rank.winningMoney * count.toLong()
            }
        )
    }

    fun calculateMarginRate(winningPrize: Money, purchaseAmount: Money): Margin =
        Margin(winningPrize.amount * 1000 / purchaseAmount.amount / 1000.0)
}

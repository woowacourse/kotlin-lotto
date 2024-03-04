package lotto.domain.model

data class LottoDrawingResult(private val originStatistics: Map<Rank, Int>) {
    val resultStatistics: Map<Rank, Int> =
        Rank.entries.associateWith { rank -> originStatistics.getOrDefault(rank, DEFAULT_COUNT) }

    fun calculateMarginRate(purchaseAmount: Money): Margin {
        val winningPrize = calculateTotalPrize()
        return Margin((winningPrize.amount / purchaseAmount.amount.toDouble()))
    }

    fun calculateTotalPrize(): Money {
        return Money(
            originStatistics.entries.sumOf { (rank, count) ->
                rank.winningMoney * count.toLong()
            }
        )
    }

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}

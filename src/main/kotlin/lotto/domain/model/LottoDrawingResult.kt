package lotto.domain.model

data class LottoDrawingResult(private val statistics: Map<Rank, Int>) {

    fun getCountByRank(rank: Rank): Int {
        return statistics[rank] ?: DEFAULT_COUNT
    }

    fun calculateTotalPrize(): Money {
        return Money(
            statistics.entries.sumOf { (rank, count) ->
                rank.winningMoney * count.toLong()
            }
        )
    }

    fun calculateEarningRate(winningPrice: Money, purchaseAmount: Money): Double {
        return winningPrice.amount / purchaseAmount.amount.toDouble()
    }

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}

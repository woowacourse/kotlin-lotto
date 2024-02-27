package lotto.domain.model

data class LottoDrawingResult(private val input: Map<Rank, Int>) {
    val statistics: Map<Rank, Int> = Rank.entries.associateWith { rank -> input.getOrDefault(rank, DEFAULT_COUNT) }

    fun calculateTotalPrize(): Money {
        return Money(
            input.entries.sumOf { (rank, count) ->
                rank.winningMoney * count.toLong()
            }
        )
    }

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}

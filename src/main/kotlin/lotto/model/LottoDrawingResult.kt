package lotto.model

data class LottoDrawingResult(val statistics: Map<Rank, Int>) {

    fun calculateTotalPrize(): Money {
        return Money(statistics.entries.sumOf { (rank, count) -> rank.winningMoney * count.toLong() })
    }
}

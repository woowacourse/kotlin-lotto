package lotto.model

data class LottoResult(private val winningStatistics: Map<Rank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            winningStatistics.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = winningStatistics.entries.sumOf { (_, count) -> count }
        return totalProfit.toDouble() / (totalCount * Lotto.LOTTO_PRICE)
    }

    fun getWinningCountBy(rank: Rank): Int {
        return winningStatistics[rank] ?: 0
    }
}

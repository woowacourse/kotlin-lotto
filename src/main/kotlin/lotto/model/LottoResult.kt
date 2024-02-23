package lotto.model

data class LottoResult(private val winningCountsByRank: Map<Rank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            winningCountsByRank.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = winningCountsByRank.entries.sumOf { (_, count) -> count }
        return totalProfit.toDouble() / (totalCount * Lotto.LOTTO_PRICE)
    }

    fun getWinningCountBy(rank: Rank): Int {
        return winningCountsByRank[rank] ?: 0
    }
}

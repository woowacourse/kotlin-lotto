package lotto.model

data class Result(private val winningCountsByRank: MutableMap<Rank, Int> = mutableMapOf()) {
    fun incrementRankCount(
        rank: Rank,
        value: Int = 1,
    ) {
        winningCountsByRank[rank] = winningCountsByRank.getOrDefault(rank, 0) + value
    }

    fun getWinningCountByRank(rank: Rank): Int {
        return winningCountsByRank.getOrDefault(rank, 0)
    }

    fun calculateProfitRate(totalPurchaseCost: Int): Double {
        val totalProfit =
            winningCountsByRank.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        return totalProfit.toDouble() / totalPurchaseCost
    }
}

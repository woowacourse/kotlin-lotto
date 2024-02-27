package lotto.model

data class LottoResult(val winningCountsByLottoRank: Map<LottoRank, Int>) {
    fun getProfitRate(totalPurchaseCost: Int): Double {
        val totalProfit =
            winningCountsByLottoRank.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        return totalProfit.toDouble() / totalPurchaseCost
    }
}

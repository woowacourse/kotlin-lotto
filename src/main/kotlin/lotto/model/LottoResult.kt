package lotto.model

data class LottoResult(val winningCountsByLottoRank: Map<LottoRank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            winningCountsByLottoRank.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = winningCountsByLottoRank.entries.sumOf { (_, count) -> count }
        return totalProfit.toDouble() / (totalCount * Lotto.LOTTO_PRICE)
    }
}

package lotto.model

data class LottoResult(val static: Map<Rank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            static.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = static.entries.sumOf { (_, count) -> count }
        return totalProfit.toDouble() / (totalCount * LOTTO_PRICE)
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

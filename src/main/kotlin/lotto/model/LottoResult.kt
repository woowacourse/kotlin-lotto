package lotto.model

class LottoResult(val static: Map<Rank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            static.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = static.entries.sumOf { (_, count) -> count }
        return (totalProfit.toDouble() / (totalCount * PURCHASE_UNIT)) * RATE
    }

    companion object {
        private const val PURCHASE_UNIT = 1_000
        private const val RATE = 100
    }
}

package lotto.model

class LottoResult(val static: Map<Rank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            static.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        return (totalProfit.toDouble() / (static.entries.size * PURCHASE_UNIT)) * RATE
    }

    companion object {
        const val PURCHASE_UNIT = 1_000
        const val RATE = 100
    }
}

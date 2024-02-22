package lotto.model

import lotto.constant.LottoConstant

data class LottoResult(val static: Map<Rank, Int>) {
    fun getProfitRate(): Double {
        val totalProfit =
            static.entries.sumOf { (rank, count) ->
                rank.winningMoney * count
            }
        val totalCount = static.entries.sumOf { (_, count) -> count }
        return (totalProfit.toDouble() / (totalCount * LottoConstant.PRICE)) * RATE
    }

    companion object {
        private const val RATE = 100
    }
}

package domain.model

import domain.model.price.PurchasePrice

class LottoMatchResult(
    private val winningCountByRank: Map<Rank, Int>,
) {
    fun getWinningCount(rank: Rank): Int {
        return winningCountByRank[rank] ?: 0
    }

    fun getProfitRate(money: PurchasePrice): Double {
        val totalPrice: Double =
            winningCountByRank
                .map { (rank, amount) ->
                    rank.winningMoney * amount
                }.sum()
                .toDouble()
        return totalPrice / money.value
    }
}

package domain.model

import domain.model.price.PurchasePrice

class LottoMatchResult(
    private val winningCountByRank: Map<Rank, Int>,
) {
    fun getWinningCount(rank: Rank): Int {
        return winningCountByRank[rank] ?: throw IllegalArgumentException(NOT_EXIST_RANK.format(rank))
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

    companion object {
        const val NOT_EXIST_RANK = "당첨 순위가 존재하지 않습니다: %s"
    }
}

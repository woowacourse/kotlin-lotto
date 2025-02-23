package domain.model

class LottoMatchResult(
    val winningCountByRank: Map<Rank, Int>,
) {
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

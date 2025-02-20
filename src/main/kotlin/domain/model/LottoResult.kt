package domain.model

class LottoResult(
    val result: MutableMap<Rank, Int> =
        Rank.entries
            .reversed()
            .associateWith { 0 }
            .toMutableMap(),
) {
    fun getProfitRate(purchasePrice: PurchasePrice): String {
        val totalPrice: Double =
            result
                .map { (rank, amount) ->
                    rank.winningMoney * amount
                }.sum()
                .toDouble()
        return ROUND.format(totalPrice / purchasePrice.value)
    }

    companion object {
        const val ROUND = "%.2f"
    }
}

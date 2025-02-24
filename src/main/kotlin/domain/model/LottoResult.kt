package domain.model

class LottoResult(
    val result: Map<Rank, Int>,
) {
    override fun toString(): String =
        result
            .map {
                getWinningMessage(it.key, it.value)
            }.joinToString("\n")

    fun getProfitRate(purchasePrice: PurchasePrice): String {
        val totalPrice: Double =
            result
                .map { (rank, amount) ->
                    rank.winningMoney * amount
                }.sum()
                .toDouble()
        return ROUND.format(totalPrice / purchasePrice.value)
    }

    private fun getWinningMessage(
        rank: Rank,
        matchCount: Int,
    ): String =
        when (rank) {
            Rank.MISS -> EMPTY_VALUE
            Rank.SECOND -> MESSAGE_BONUS_BALL_MATCH.format(rank.countOfMatch, rank.winningMoney, matchCount)
            else -> MESSAGE_EACH_RANK_RESULT.format(rank.countOfMatch, rank.winningMoney, matchCount)
        }

    companion object {
        val DEFAULT_RESULT: Map<Rank, Int> = Rank.entries.reversed().associateWith { 0 }
        const val EMPTY_VALUE = ""
        const val ROUND = "%.2f"
        const val MESSAGE_EACH_RANK_RESULT = "%d개 일치 (%d원)- %d개"
        const val MESSAGE_BONUS_BALL_MATCH = "%d개 일치, 보너스 볼 일치(%d원) - %d개"
    }
}

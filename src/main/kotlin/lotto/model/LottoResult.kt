package lotto.model

class LottoResult(
    val ranks: Map<Rank, Int>,
) {
    fun getRateOfReturn(purchaseAmount: Int): Double = getTotalPrizeMoney() / purchaseAmount.toDouble()

    private fun getTotalPrizeMoney(): Long =
        ranks
            .entries
            .sumOf { (rank, count) -> rank.prizeMoney * count }
            .toLong()

    fun getIsLossMoney(rateOfReturn: Double): Boolean = rateOfReturn < PRINCIPAL_RATE_OF_RETURN

    companion object {
        private const val PRINCIPAL_RATE_OF_RETURN = 1
    }
}

package lottogame.model

class LottoGameResult private constructor(
    val results: List<RankResult>,
) {
    fun calculateEarningRate(purchaseExpense: Money): Double {
        val earning = results.sumOf { it.rank.winningMoney * it.count }
        val expense = purchaseExpense.amount
        val earningRate: Double = earning / expense.toDouble()
        return earningRate
    }

    data class RankResult(
        val rank: Rank,
        val count: Int,
    )

    companion object {
        private const val INIT_RANK_COUNT = 0

        fun of(
            bonusNumber: LottoNumber,
            winningLotto: Lotto,
            purchasedLottie: List<Lotto>,
        ): LottoGameResult {
            val ranks = generateRanks(purchasedLottie, winningLotto, bonusNumber)
            val rankResults: List<RankResult> =
                getRankMap()
                    .apply { ranks.forEach { merge(it, 1, Int::plus) } }
                    .toRankResults()
            return LottoGameResult(rankResults)
        }

        private fun getRankMap(): MutableMap<Rank, Int> {
            return Rank.entries
                .reversed()
                .associateWith { INIT_RANK_COUNT }
                .toMutableMap()
        }

        private fun Map<Rank, Int>.toRankResults() = toList().map { (rank, count) -> RankResult(rank, count) }

        private fun generateRanks(
            purchasedLottie: List<Lotto>,
            winningLotto: Lotto,
            bonusNumber: LottoNumber,
        ): List<Rank> =
            purchasedLottie.map { lotto ->
                val matchCount = lotto.getMatchCount(winningLotto)
                val hasBonusNumber = bonusNumber in lotto
                Rank.valueOf(matchCount, hasBonusNumber)
            }
    }
}

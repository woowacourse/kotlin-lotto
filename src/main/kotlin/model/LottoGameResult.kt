package model

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
        operator fun invoke(
            bonusNumber: LottoNumber,
            winningLotto: Lotto,
            purchasedLottie: List<Lotto>,
        ): LottoGameResult {
            val ranks =
                purchasedLottie.map { lotto ->
                    val matchCount = lotto.countMatch(winningLotto)
                    val hasBonusNumber = bonusNumber in lotto
                    Rank.valueOf(matchCount, hasBonusNumber)
                }
            val rankMap: Map<Rank, Int> = ranks.groupingBy { it }.eachCount()
            val rankResults: List<RankResult> =
                rankMap.toList()
                    .map { (rank, count) -> RankResult(rank, count) }
            return LottoGameResult(rankResults)
        }
    }
}

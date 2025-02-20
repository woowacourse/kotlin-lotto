package lotto.model

import kotlin.math.floor

class LottoResult {
    fun getWinningCounts(
        lottoBundle: List<Lotto>,
        winningLotto: WinningLotto,
    ): Map<Rank, Int> {
        val result = Rank.entries.associateWith { 0 }.toMutableMap()
        lottoBundle.forEach { lotto ->
            val rank = Rank.fromMatch(winningLotto.getCountOfMatch(lotto), winningLotto.isBonusNumberMatch(lotto))
            result[rank] = result[rank]!! + 1
        }
        return result
    }

    private fun getSum(winningCounts: Map<Rank, Int>): Int =
        winningCounts.entries.sumOf { (rank, winningCount) -> winningCount * rank.winningMoney }

    fun calculateProfit(
        input: String,
        winningCounts: Map<Rank, Int>,
    ): String {
        val sum = getSum(winningCounts)
        val purchaseAmount = input.toDouble()
        return (floor((sum.toDouble() / purchaseAmount) * PERCENT_FACTOR) / PERCENT_FACTOR).toString()
    }

    companion object {
        private const val PERCENT_FACTOR = 100
    }
}

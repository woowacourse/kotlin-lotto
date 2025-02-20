package lotto.domain

import lotto.util.Rank
import kotlin.math.floor

class LottoResult(private val winningNumber: List<Int>, private val bonusNumber: Int) {
    fun matchLotto(lottos: List<Lotto>): Map<Rank, Int> {
        val winningStats: MutableMap<Rank, Int> =
            mutableMapOf(
                Rank.FIFTH to 0,
                Rank.FOURTH to 0,
                Rank.THIRD to 0,
                Rank.SECOND to 0,
                Rank.FIRST to 0,
            )

        lottos.forEach { lotto ->
            val count: Int = compareLotto(lotto.numbers)
            val bonus: Boolean = checkBonusNumber(lotto.numbers)
            val rankState: Rank? = Rank.getRankState(count, bonus)
            updateWinningStats(rankState, winningStats)
        }
        return winningStats.toMap()
    }

    private fun updateWinningStats(
        rankState: Rank?,
        winningStats: MutableMap<Rank, Int>,
    ) {
        if (rankState == null || rankState == Rank.NONE) return
        winningStats[rankState] = (winningStats[rankState] ?: 0) + 1
        println(winningStats[rankState])
    }

    fun compareLotto(lottos: List<Int>): Int {
        return lottos.intersect(winningNumber).size
    }

    fun checkBonusNumber(lottos: List<Int>): Boolean {
        return lottos.contains(bonusNumber)
    }

    fun calculatePrize(winningStats: Map<Rank, Int>): Long {
        var totalPrize: Long = 0

        winningStats.forEach { (state, count) ->
            totalPrize += state.price * count
        }

        return totalPrize
    }

    fun calculateProfit(
        totalPrize: Long,
        purchaseAmount: Int,
    ): Double {
        val result = totalPrize / purchaseAmount.toDouble() * 100
        return floor(result) / 100
    }
}

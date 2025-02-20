package lotto.domain

import lotto.util.Rank

class LottoResult(private val winningNumber: List<Int>, private val bonusNumber: Int) {
    fun matchLotto(lottos: List<Lotto>): Map<Rank, Int> {
        val winningStats: MutableMap<Rank, Int> =
            mutableMapOf(
                Rank.FIFTH to DEFAULT_VALUE,
                Rank.FOURTH to DEFAULT_VALUE,
                Rank.THIRD to DEFAULT_VALUE,
                Rank.SECOND to DEFAULT_VALUE,
                Rank.FIRST to DEFAULT_VALUE,
            )

        lottos.forEach { lotto ->
            val count: Int = compareLotto(lotto.numbers)
            val bonus: Boolean = checkBonusNumber(lotto.numbers)
            val rankState: Rank = Rank.getRankState(count, bonus)
            updateWinningStats(rankState, winningStats)
        }
        return winningStats.toMap()
    }

    private fun updateWinningStats(
        rankState: Rank,
        winningStats: MutableMap<Rank, Int>,
    ) {
        if (rankState == Rank.NONE) return
        winningStats[rankState] = (winningStats[rankState] ?: DEFAULT_VALUE) + INCREASED_VALUE
        println(winningStats[rankState])
    }

    fun compareLotto(lottos: List<Int>): Int {
        return lottos.intersect(winningNumber).size
    }

    fun checkBonusNumber(lottos: List<Int>): Boolean {
        return lottos.contains(bonusNumber)
    }

    fun calculatePrize(winningStats: Map<Rank, Int>): Long {
        var totalPrize: Long = DEFAULT_VALUE.toLong()

        winningStats.forEach { (state, count) ->
            totalPrize += state.price * count
        }

        return totalPrize
    }

    fun calculateProfit(
        totalPrize: Long,
        purchaseAmount: Int,
    ): Double {
        return totalPrize / purchaseAmount.toDouble()
    }

    companion object {
        const val DEFAULT_VALUE = 0
        const val INCREASED_VALUE = 1
    }
}

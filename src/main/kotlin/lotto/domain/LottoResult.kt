package lotto.domain

import lotto.util.Rank

class LottoResult(private val winningNumber: List<Int>, private val bonusNumber: Int) {
    var winningStats: MutableMap<Rank, Int> =
        mutableMapOf(
            Rank.FIRST to 0,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 0,
        )

    fun matchLotto(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            val count: Int = compareLotto(lotto.numbers)
            val bonus: Boolean = checkBonusNumber(lotto.numbers)
            val rankState: Rank? = Rank.getRankState(count, bonus)
            updateWinningStats(rankState)
        }
    }

    private fun updateWinningStats(rankState: Rank?) {
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

    fun calculatePrize(): Long {
        var totalPrize: Long = 0

        winningStats.forEach { (state, count) ->
            totalPrize += state.price * count
        }

        return totalPrize
    }
}

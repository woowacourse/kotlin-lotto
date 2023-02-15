package lotto.domain

import kotlin.math.floor

class WinningResult(
    val countMatchRanks: MutableList<Int> = MutableList(6) { 0 }
) {
    fun calculateYield(amount: Int): Double {
        val prize = calculateTotalPrize()
        val yield = (prize.toDouble() / amount) * 100
        return floor(yield) / 100
    }

    fun countRank(rank: Rank) {
        when (rank) {
            Rank.FIRST -> countMatchRanks[0]++
            Rank.SECOND -> countMatchRanks[1]++
            Rank.THIRD -> countMatchRanks[2]++
            Rank.FOURTH -> countMatchRanks[3]++
            Rank.FIFTH -> countMatchRanks[4]++
            else -> countMatchRanks[5]++
        }
    }

    fun isGain(yield: Double): Boolean = yield >= 1

    private fun calculateTotalPrize(): Long {
        var totalPrize: Long = 0

        totalPrize += Rank.FOURTH.winningMoney * countMatchRanks[0]
        totalPrize += Rank.SECOND.winningMoney * countMatchRanks[1]
        totalPrize += Rank.THIRD.winningMoney * countMatchRanks[2]
        totalPrize += Rank.FOURTH.winningMoney * countMatchRanks[3]
        totalPrize += Rank.FIFTH.winningMoney * countMatchRanks[4]

        return totalPrize
    }
}

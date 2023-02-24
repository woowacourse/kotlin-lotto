package lotto.domain

import kotlin.math.floor

class WinningResult(
    private val countMatchRanks: Map<Rank, Int>,
    amount: Int
) {
    val yield: Double = calculateYield(amount)
    val isGain: Boolean = isGain(yield)

    operator fun get(rank: Rank): Int = countMatchRanks[rank] ?: 0

    fun toMap(): Map<Rank, Int> = countMatchRanks

    private fun calculateYield(amount: Int): Double {
        val prize = calculateTotalPrize()
        val yield = (prize.toDouble() / amount) * 100
        return floor(yield) / 100
    }

    private fun isGain(yield: Double): Boolean = yield >= 1

    private fun calculateTotalPrize(): Long {
        return countMatchRanks.map { it.key.winningMoney.toLong() * it.value }.sum()
    }
}

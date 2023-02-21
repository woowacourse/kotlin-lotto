package lotto.domain

import kotlin.math.floor

class WinningResult(
    private val countMatchRanks: MutableMap<Rank, Int> =
        Rank.values().associateWith { 0 }.toMutableMap()
) {

    operator fun get(rank: Rank): Int = countMatchRanks[rank] ?: 0

    fun calculateYield(amount: Int): Double {
        val prize = calculateTotalPrize()
        val yield = (prize.toDouble() / amount) * 100
        return floor(yield) / 100
    }

    fun countRank(rank: Rank) {
        countMatchRanks[rank] = countMatchRanks[rank]!!.plus(1)
    }

    fun isGain(yield: Double): Boolean = yield >= 1

    fun getResult(): List<String> {
        val texts = mutableListOf<String>()
        Rank.values().associateWith {
            if (it != Rank.MISS)
                texts.add("${it.description} (${it.winningMoney})- ${countMatchRanks[it]}")
        }

        return texts
    }

    private fun calculateTotalPrize(): Long {
        var sum: Long = 0

        countMatchRanks.forEach {
            sum += it.key.winningMoney * it.value
        }

        return sum
    }
}

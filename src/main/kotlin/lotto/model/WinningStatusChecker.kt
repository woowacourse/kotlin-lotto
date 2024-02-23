package lotto.model

import lotto.util.WinningRank

class WinningStatusChecker(results: List<WinningRank>) {
    private val status: MutableMap<WinningRank, Int> = mutableMapOf()

    init {
        results.forEach { rank ->
            status[rank] = status.getOrDefault(rank, DEFAULT_MATCH_COUNT) + INCREASING_COUNT
        }
    }

    override fun toString(): String =
        WinningRank.entries.filter { it != WinningRank.MISS }.joinToString("\n") {
            "${WinningRank.formatByRank(it)} - ${status.getOrDefault(it, DEFAULT_MATCH_COUNT)}개"
        }

    fun getEarningRate(): Double {
        val total: Long = status.entries.sumOf { it.key.winningMoney * it.value }
        return (total / (status.values.sum() * LottoMachine.TICKET_PRICE).toDouble())
    }

    companion object {
        const val DEFAULT_MATCH_COUNT = 0
        const val INCREASING_COUNT = 1
    }
}

package lotto.model

import lotto.util.WinningRank

class WinningStatusChecker(results: List<WinningRank>) {
    private val status: MutableMap<WinningRank, Int> = mutableMapOf()

    init {
        results.forEach { rank ->
            status[rank] = status.getOrDefault(rank, DEFAULT_MATCH_COUNT) + INCREASING_COUNT
        }
    }

    fun getWinningCountsByRank(winningRank: WinningRank): String = status.getOrDefault(winningRank, DEFAULT_MATCH_COUNT).toString()

    fun getEarningRate(): Double {
        val total: Long = status.entries.sumOf { it.key.winningMoney * it.value }
        return (total / (status.values.sum() * LottoMachine.TICKET_PRICE).toDouble())
    }

    companion object {
        const val DEFAULT_MATCH_COUNT = 0
        const val INCREASING_COUNT = 1
    }
}

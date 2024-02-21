package lotto.model

import lotto.util.WinningRank

class WinningStatusChecker(results: List<WinningRank>) {
    private val status: MutableMap<WinningRank, Int> = mutableMapOf()

    init {
        results.forEach { rank ->
            status[rank] = status.getOrDefault(rank, 0) + 1
        }
    }

    fun getEarningRate(): Double {
        val total: Long =  status.entries.sumOf { it.key.winningMoney * it.value }
        return (total / (status.values.sum() * 1000).toDouble())
    }
}

package model

import model.domain.Rank

class LottoResult(private val rankList: List<Rank>) {
    private val _result: MutableMap<Rank, Int> = mutableMapOf()
    val result: Map<Rank, Int> get() = _result.toMap()

    init {
        readyLottoResult()
    }

    private fun updateRankCount(rank: Rank) {
        _result[rank] = (_result[rank] ?: NULL) + COUNT_UP
    }

    fun getProfitRate(): Double {
        var profit = DOUBLE_ZERO
        var countOfTicket = DOUBLE_ZERO
        _result.forEach { (rank, count) ->
            profit += (rank.winningMoney * count)
            countOfTicket += count
        }

        return (profit / (countOfTicket * ONE_LOTTO_PRICE))
    }

    private fun readyLottoResult() {
        rankList.forEach { rank ->
            updateRankCount(rank)
        }
    }

    companion object {
        private const val COUNT_UP = 1
        private const val NULL = 0
        private const val DOUBLE_ZERO = 0.00
        private const val ONE_LOTTO_PRICE = 1000
    }
}

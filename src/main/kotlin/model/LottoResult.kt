package model

import domain.Rank

class LottoResult {
    private val _result: MutableMap<Rank, Int> = mutableMapOf()
    val result: Map<Rank, Int> get() = _result

    fun updateRankCount(rank: Rank) {
        _result[rank] = (_result[rank] ?: NULL) + 1
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

    companion object {
        private const val NULL = 0
        private const val DOUBLE_ZERO = 0.00
        private const val ONE_LOTTO_PRICE = 1000
    }
}

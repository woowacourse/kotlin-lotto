package model

import domain.Rank

class LottoResult {
    val result = HashMap<Rank, Int>()
    fun updateLottoResult(rank: Rank) {
        this.result[rank] = (this.result[rank] ?: INITIALIZE_TO_ZERO) + PLUS_ONE
    }

    fun getProfitRate(): Double {
        var profit = DOUBLE_ZERO
        var countOfTicket = DOUBLE_ZERO
        this.result.forEach { (rank, count) ->
            profit += (rank.winningMoney * count)
            countOfTicket += count
        }

        return (profit / (countOfTicket * ONE_LOTTO_PRICE))
    }

    companion object {
        private const val INITIALIZE_TO_ZERO = 0
        private const val DOUBLE_ZERO = 0.00
        private const val ONE_LOTTO_PRICE = 1000
        private const val PLUS_ONE = 1
    }
}

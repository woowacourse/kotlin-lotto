package model

import domain.Rank

class LottoResult {
    var result: HashMap<Rank, Int> = hashMapOf()

    fun plusRankCount(rank: Rank, lottoResult: HashMap<Rank, Int>) {
        lottoResult[rank] = (lottoResult[rank] ?: NULL) + 1
    }

    fun getProfitRate(result: HashMap<Rank, Int>): Double {
        var profit = DOUBLE_ZERO
        var countOfTicket = DOUBLE_ZERO
        result.forEach { (rank, count) ->
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

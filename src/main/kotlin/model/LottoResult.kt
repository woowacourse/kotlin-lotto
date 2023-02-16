package model

import domain.Rank

class LottoResult {
    var result: HashMap<Rank, Int> = hashMapOf()

    fun plusRankCount(rank: Rank, lottoResult: HashMap<Rank, Int>) {
        lottoResult[rank] = (lottoResult[rank] ?: 0) + 1
    }

    fun getProfitRate(result: HashMap<Rank, Int>): Double {
        var profit = 0.00
        var countOfTicket = 0.00
        result.forEach { (rank, count) ->
            profit += (rank.winningMoney * count)
            countOfTicket += count
        }

        return (profit / (countOfTicket * 1000))
    }

    companion object {
        private const val COUNT_ONE = 1
        private const val RESULT_CONTAINER_NULL_ERROR = "[ERROR] 비정상적인 오류가 발생했습니다."
    }
}

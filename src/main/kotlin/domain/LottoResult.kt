package domain

class LottoResult() {

    private var resultContainer: HashMap<Rank, Int> = hashMapOf(Rank.THIRD to 0)
    val _resultContainer get() = resultContainer

    fun plusRankCount(rank: Rank) {
        resultContainer[rank] =
            (resultContainer[rank] ?: throw IllegalArgumentException(RESULT_CONTAINER_NULL_ERROR)) + COUNT_ONE
    }

    fun getProfitRate(result :HashMap<Rank, Int> ):Double{
        var profit = 0
        var countOfTicket = 0
        result.forEach { (rank, count) ->
            profit += (rank.winningMoney * count)
            countOfTicket += count
        }

        println((profit / (countOfTicket * 1000))*100.toDouble())
        return (profit / (countOfTicket * 1000))*100.toDouble()
    }

    companion object {
        private const val COUNT_ONE = 1
        private const val RESULT_CONTAINER_NULL_ERROR = "[ERROR] 비정상적인 오류가 발생했습니다."
    }
}
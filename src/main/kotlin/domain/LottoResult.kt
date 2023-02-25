package domain

class LottoResult(private val result: Map<Rank, Int>) {

    init {
        require(Rank.values().all { rank -> result[rank] != null }) {
            ERROR_LOTTO_RESULT_IS_NOT_CONTAIN_ALL
        }
    }

    fun sumWinningMoney(): Double {
        var sum = 0.0
        for ((rank, count) in result) {
            sum += rank.winningMoney * count
        }
        return sum
    }

    operator fun get(rank: Rank): Int = result[rank] ?: 0

    companion object {
        private const val ERROR_LOTTO_RESULT_IS_NOT_CONTAIN_ALL =
            "[ERROR] 각 등수에 대한 개수 중 널값이 있어서는 안됩니다. 만약 해당 등수에 당첨된 적이 없다면 0을 넣어주세요."
    }
}

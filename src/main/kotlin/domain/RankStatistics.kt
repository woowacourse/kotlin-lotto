package domain

class RankStatistics {
    private val rankCount = HashMap<Rank, Int>()

    fun updateRankCount(rank: Rank) {
        this.rankCount[rank] = (this.rankCount[rank] ?: INITIALIZE_TO_ZERO) + PLUS_ONE
    }

    fun getRankCount(rank: Rank): Int {
        val count = runCatching { return this.rankCount.getValue(rank) }.getOrNull()
        return count ?: INITIALIZE_TO_ZERO
    }

    fun getProfitRate(): Double {
        var profit = INITIALIZE_TO_DOUBLE_ZERO
        var totalCount = INITIALIZE_TO_ZERO
        this.rankCount.forEach { (rank, count) ->
            profit += rank.winningMoney * count
            totalCount += count
        }
        return profit / (totalCount * ONE_LOTTO_PRICE)
    }

    fun isProfitable(profit: Double): Boolean = profit < MINIMUM_PROFITABLE_NUM

    companion object {
        const val INITIALIZE_TO_ZERO = 0
        const val INITIALIZE_TO_DOUBLE_ZERO = 0.00
        const val PLUS_ONE = 1
        const val ONE_LOTTO_PRICE = 1000
        const val MINIMUM_PROFITABLE_NUM = 1
    }
}

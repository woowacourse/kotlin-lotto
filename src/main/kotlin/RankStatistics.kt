class RankStatistics(val moeny: Int) : PaymentMoney(moeny) {
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
        this.rankCount.forEach { (rank, count) ->
            profit += rank.winningMoney * count
        }
        return profit / moeny
    }

    companion object {
        const val INITIALIZE_TO_ZERO = 0
        const val INITIALIZE_TO_DOUBLE_ZERO = 0.00
        const val PLUS_ONE = 1
    }
}

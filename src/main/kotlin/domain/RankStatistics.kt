package domain

class RankStatistics(private val ranks: List<Rank>) {
    private val rankInformation: HashMap<Rank, Count> = HashMap()

    init {
        initRankInformation()
    }

    private fun updateRankCount(rank: Rank) {
        rankInformation[rank] = (rankInformation[rank] ?: Count(INITIALIZE_TO_ZERO)) + PLUS_ONE
    }

    private fun initRankInformation() {
        ranks.forEach { rank ->
            updateRankCount(rank)
        }
    }

    fun getRankCount(rank: Rank): Int {
        return rankInformation[rank]?.value ?: INITIALIZE_TO_ZERO
    }

    fun getProfitRate(): Double {
        var profit = INITIALIZE_TO_DOUBLE_ZERO
        rankInformation.forEach { (rank, count) ->
            profit += rank.winningMoney * count.value
        }
        return profit / (ranks.size * ONE_LOTTO_PRICE)
    }

    fun isProfitable(): Boolean = getProfitRate() < MINIMUM_PROFITABLE_NUM

    companion object {
        private const val INITIALIZE_TO_ZERO = 0
        private const val INITIALIZE_TO_DOUBLE_ZERO = 0.00
        private const val PLUS_ONE = 1
        private const val ONE_LOTTO_PRICE = 1000
        private const val MINIMUM_PROFITABLE_NUM = 1
    }
}

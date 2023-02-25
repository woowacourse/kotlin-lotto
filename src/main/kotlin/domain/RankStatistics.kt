package domain

class RankStatistics(private val ranks: List<Rank>) {
    private lateinit var _rankInformation: HashMap<Rank, Count>
    val rankInformation: HashMap<Rank, Count>
        get() = _rankInformation.deepCopy()

    init {
        updateRankInformation()
    }

    private fun updateRankCount(rank: Rank) {
        _rankInformation[rank] = (_rankInformation[rank] ?: Count(INITIALIZE_TO_ZERO)) + PLUS_ONE
    }

    private fun updateRankInformation() {
        _rankInformation = hashMapOf()
        ranks.forEach { rank ->
            updateRankCount(rank)
        }
    }

    fun getRankCount(rank: Rank): Int {
        val count = runCatching { return rankInformation.getValue(rank).value }.getOrNull()
        return count ?: Count(INITIALIZE_TO_ZERO).value
    }

    fun getProfitRate(): Double {
        var profit = INITIALIZE_TO_DOUBLE_ZERO
        _rankInformation.forEach { (rank, count) ->
            profit += rank.winningMoney * count.value
        }
        return profit / (ranks.size * ONE_LOTTO_PRICE)
    }

    fun isProfitable(): Boolean = getProfitRate() < MINIMUM_PROFITABLE_NUM

    fun HashMap<Rank, Count>.deepCopy(): HashMap<Rank, Count> {
        val copy: HashMap<Rank, Count> = HashMap()
        this.forEach { (key, value) -> copy[key] = value }
        return copy
    }

    companion object {
        const val INITIALIZE_TO_ZERO = 0
        const val INITIALIZE_TO_DOUBLE_ZERO = 0.00
        const val PLUS_ONE = 1
        const val ONE_LOTTO_PRICE = 1000
        const val MINIMUM_PROFITABLE_NUM = 1
    }
}

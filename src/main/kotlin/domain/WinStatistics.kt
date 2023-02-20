package domain

class WinStatistics(comparingResults: List<ComparingResult>) {

    private val _rankCount = Rank.values().associateWith { 0 }.toMutableMap()
    val rankCount: Map<Rank, Int>
        get() = _rankCount.toMap()

    init {
        comparingResults.forEach {
            val rank = Rank.valueOf(it.matchedCount, it.isBonusMatched)
            _rankCount[rank] = _rankCount[rank]?.plus(1)
                ?: throw IllegalArgumentException("잘못된 Rank 참조입니다.")
        }
    }

    fun calculateTotalIncome(): TotalPrize {
        var total: Long = 0L
        _rankCount.forEach {
            total += it.key.winningMoney * it.value.toLong()
        }

        return TotalPrize(total)
    }
}

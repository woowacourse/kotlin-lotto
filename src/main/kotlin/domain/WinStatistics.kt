package domain

class WinStatistics(comparingResults: List<ComparingResultDto>) {
    val rankCount: Map<Rank, Int>

    init {
        rankCount = comparingResults
            .groupingBy { Rank.valueOf(it.matchedCount, it.isBonusMatched) }
            .eachCount()
    }

    private fun getTotalIncome(): Money {
        return Money(
            rankCount
                .map { it.key.winningMoney * it.value.toLong() }
                .sum(),
        )
    }

    fun calculateEarningRate(spentMoney: Money): Double {
        return getTotalIncome() / spentMoney
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WinStatistics

        if (rankCount != other.rankCount) return false

        return true
    }

    override fun hashCode(): Int {
        return rankCount.hashCode()
    }
}

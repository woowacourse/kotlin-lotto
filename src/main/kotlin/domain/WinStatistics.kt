package domain

class WinStatistics(_rankCount: Map<Rank, Int>) {
    private val _rankCount: MutableMap<Rank, Int>
    val rankCount: Map<Rank, Int> get() = _rankCount

    init {
        this._rankCount = _rankCount.toMutableMap()
    }

    fun plusValue(key: Rank, value: Int) {
        _rankCount[key] = (_rankCount[key] ?: 0).plus(value)
    }

    fun getTotalIncome(): Money {
        return Money(
            rankCount
                .map { it.key.winningMoney * it.value.toLong() }
                .sum(),
        )
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

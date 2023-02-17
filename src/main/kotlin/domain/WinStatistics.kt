package domain

data class WinStatistics(private val _rankCount: MutableMap<Rank, Int>) {
    val rankCount: Map<Rank, Int> get() = _rankCount

    fun plusValue(key: Rank, value: Int) {
        _rankCount[key] = (_rankCount[key] ?: 0).plus(value)
    }

    fun getTotalIncome(): Money {
        var total: Long = 0L

        rankCount.forEach {
            total += it.key.winningMoney * it.value.toLong()
        }

        return Money(total)
    }
}

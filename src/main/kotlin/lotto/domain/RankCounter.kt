package lotto.domain

class RankCounter(
    numberOfEachRank: Map<String, Int> =
        Rank.values().associate { it.name to NUMBER_DEFAULT_VALUE }
) {
    private val _numberOfEachRank: MutableMap<String, Int> = numberOfEachRank.toMutableMap()
    val numberOfEachRank: Map<String, Int>
        get() = _numberOfEachRank.toMap()

    fun increaseNumber(rank: Rank) {
        val key = rank.name
        if (_numberOfEachRank.containsKey(key)) {
            _numberOfEachRank[key] = _numberOfEachRank.getOrDefault(key, NUMBER_DEFAULT_VALUE) + INCREASE_VALUE
        }
    }

    fun calculateTotalPrize(): Long {
        var prize = 0L
        numberOfEachRank.forEach { (name, count) ->
            prize += Rank.valueOf(name).calculatePrize(count)
        }
        return prize
    }

    companion object {
        private const val NUMBER_DEFAULT_VALUE = 0
        private const val INCREASE_VALUE = 1
    }
}

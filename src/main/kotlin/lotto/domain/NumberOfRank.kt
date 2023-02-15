package lotto.domain

class NumberOfRank(
    numbers: Map<String, Int> =
        Rank.values().associate { it.name to NUMBER_DEFAULT_VALUE }
) {
    private val _numbers: MutableMap<String, Int> = numbers.toMutableMap()
    val numbers: Map<String, Int>
        get() = _numbers.toMap()

    fun increaseNumber(rank: Rank) {
        val key = rank.name
        if (_numbers.containsKey(key)) {
            _numbers[key] = _numbers.getOrDefault(key, NUMBER_DEFAULT_VALUE) + INCREASE_VALUE
        }
    }

    companion object {
        private const val NUMBER_DEFAULT_VALUE = 0
        private const val INCREASE_VALUE = 1
    }
}

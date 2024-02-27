package lotto.model

enum class Rank(val index: Int, val countOfMatch: Int, val winningMoney: Int) {
    FIRST(0, 6, 2_000_000_000),
    SECOND(1, 5, 30_000_000),
    THIRD(2, 5, 1_500_000),
    FOURTH(3, 4, 50_000),
    FIFTH(4, 3, 5_000),
    SIXTH(5, 2, 1_000),
    MISS(6, 0, 0), ;

    companion object {
        val bonusNeededRank = SECOND

        fun decideRank(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            val rankEntries = enumValues<Rank>()
            return when (countOfMatch) {
                bonusNeededRank.countOfMatch ->
                    if (matchBonus) bonusNeededRank else rankEntries[bonusNeededRank.index + 1]

                else -> entries.find { it.countOfMatch == countOfMatch } ?: MISS
            }
        }
    }
}

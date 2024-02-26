package lotto.model

enum class Rank(val index: Int, val countOfMatch: Int, val winningMoney: Int) {
    FIRST(0, 6, 2_000_000_000),
    SECOND(1, 5, 30_000_000),
    THIRD(2, 5, 1_500_000),
    FOURTH(3, 4, 50_000),
    FIFTH(4, 3, 5_000),
    MISS(5, 0, 0), ;

    companion object {
        fun decideRank(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank? {
            val rankEntries = enumValues<Rank>()
            val bonusNeededRank = SECOND
            if (countOfMatch == bonusNeededRank.countOfMatch) {
                if (matchBonus) {
                    return bonusNeededRank
                }
                return rankEntries[bonusNeededRank.index + 1]
            }
            return entries.find { it.countOfMatch == countOfMatch }
        }
    }
}

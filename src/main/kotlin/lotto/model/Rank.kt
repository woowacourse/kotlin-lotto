package lotto.model

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(3, 1_000),
    MISS(0, 0), ;

    companion object {
        val bonusNeededRanks: List<Rank> = listOf(SECOND, FIFTH)

        fun decideRank(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return when (countOfMatch) {
                in bonusNeededRanks.map { it.countOfMatch } -> judgeMatchBonus(countOfMatch, matchBonus)
                else -> entries.find { it.countOfMatch == countOfMatch } ?: MISS
            }
        }

        private fun judgeMatchBonus(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            val rankEntries = enumValues<Rank>()
            return if (matchBonus) {
                Rank.entries.first { it.countOfMatch == countOfMatch }
            } else {
                rankEntries[Rank.entries.first { it.countOfMatch == countOfMatch }.ordinal + 1]
            }
        }
    }
}

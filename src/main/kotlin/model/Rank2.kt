package model

enum class Rank2(val countOfMatch: Int, val winningMoney: Int, val isMatching: (Int, Boolean) -> Boolean) {
    FIRST(6, 2_000_000_000, { countOfMatch, _ -> FIRST.countOfMatch == countOfMatch }),
    SECOND(5, 30_000_000, { countOfMatch, matchBonus -> (SECOND.countOfMatch == countOfMatch) && matchBonus }),
    THIRD(5, 1_500_000, { countOfMatch, matchBonus -> (THIRD.countOfMatch == countOfMatch) && matchBonus }),
    FOURTH(4, 50_000, { countOfMatch, _ -> FOURTH.countOfMatch == countOfMatch }),
    FIFTH(3, 5_000, { countOfMatch, _ -> FIFTH.countOfMatch == countOfMatch }),
    MISS(0, 0, { countOfMatch, _ -> countOfMatch !in (3..6) }), ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank2 {
            return entries.find {
                it.isMatching(countOfMatch, matchBonus)
            } ?: throw IllegalArgumentException()
        }
    }
}
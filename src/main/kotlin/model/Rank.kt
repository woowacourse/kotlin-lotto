package model

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return Rank.entries.find {
                when (countOfMatch) {
                    FIRST.countOfMatch, FOURTH.countOfMatch, FIFTH.countOfMatch -> it.countOfMatch == countOfMatch
                    SECOND.countOfMatch, THIRD.countOfMatch -> isSecondOrThird(matchBonus, it)
                    else -> it.winningMoney == MISS.winningMoney
                }
            } ?: MISS
        }

        private fun isSecondOrThird(matchBonus: Boolean, it: Rank) =
            if (matchBonus) {
                it.winningMoney == SECOND.winningMoney
            } else {
                it.winningMoney == THIRD.winningMoney
            }
    }
}

package domain.rank

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank =
            if (checkRankIsThird(countOfMatch, matchBonus)) Rank.THIRD else
                Rank.values().find {
                    it.countOfMatch == countOfMatch
                } ?: Rank.MISS

        private fun checkRankIsThird(countOfMatch: Int, matchBonus: Boolean): Boolean =
            countOfMatch == Rank.THIRD.countOfMatch && !matchBonus
    }
}

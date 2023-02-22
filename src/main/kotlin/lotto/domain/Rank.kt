package lotto.domain

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    fun calculatePrize(count: Int): Long {
        return winningMoney.toLong() * count
    }

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            if (countOfMatch == 5) return decideSecondOrThird(matchBonus)
            return values().find { it.countOfMatch == countOfMatch } ?: MISS
        }

        private fun decideSecondOrThird(matchBonus: Boolean): Rank {
            if (matchBonus) return SECOND
            return THIRD
        }
    }
}

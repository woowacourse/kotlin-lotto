package lotto.model

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    companion object {
        private const val BONUS_MATCH_COUNT = 5

        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            if (countOfMatch == BONUS_MATCH_COUNT && matchBonus)
                return SECOND
            return values().findLast { it.countOfMatch == countOfMatch } ?: MISS
        }
    }
}

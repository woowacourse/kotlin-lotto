package domain.rank

enum class Rank(val countOfMatch: Int, val matchBonus: Boolean, val winningMoney: Int) {
    FIRST_WITH_BONUS(6, true, 2_000_000_000),
    FIRST_WITHOUT_BONUS(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH_WITH_BONUS(4, true, 50_000),
    FOURTH_WITHOUT_BONUS(4, false, 50_000),
    FIFTH_WITH_BONUS(3, true, 5_000),
    FIFTH_WITHOUT_BONUS(3, false, 5_000),
    MISS(0, false, 0),
    ;

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank =
            Rank.values().find { it.countOfMatch == countOfMatch && it.matchBonus == matchBonus }
                ?: MISS
    }
}

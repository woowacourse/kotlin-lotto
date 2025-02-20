package domain.model

enum class Rank(val countOfMatch: Int, val winningMoney: Int, val requiresBonusMatch: Boolean = false) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            if (matchBonus && countOfMatch == SECOND.countOfMatch) {
                return SECOND
            }
            return entries.first {
                it.countOfMatch == countOfMatch && !it.requiresBonusMatch
            }
        }
    }
}

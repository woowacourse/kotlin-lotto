package domain.model

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Int,
    val requiresBonusMatch: Boolean = false,
) {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000),
    ;

    companion object {
        fun rankMap(): Map<Rank, Int> = Rank.entries.associateWith { 0 }

        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            if (matchBonus && countOfMatch == SECOND.countOfMatch) {
                return SECOND
            }
            return entries.find {
                it.countOfMatch == countOfMatch && !it.requiresBonusMatch
            } ?: MISS
        }
    }
}

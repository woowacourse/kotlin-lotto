package lotto.domain.model

enum class Rank(val countOfMatch: Int, val winningMoney: Int, val isBonusMatch: Boolean) {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false);

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return when (countOfMatch) {
                5 -> if (matchBonus) SECOND else THIRD
                else -> entries.find { it.countOfMatch == countOfMatch } ?: MISS
            }
        }
    }
}

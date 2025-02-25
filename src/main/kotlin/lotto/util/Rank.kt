package lotto.util

enum class Rank(val countOfMatch: Int, val price: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0), ;

    companion object {
        fun getRankState(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank? {
            if (!matchBonus && countOfMatch == 5) return THIRD
            return entries.find {
                countOfMatch == it.countOfMatch
            }
        }
    }
}

package lotto.util

enum class Rank(val countOfMatch: Int, val price: Int) {
    NONE(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000), ;

    companion object {
        fun getRankState(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return when (countOfMatch) {
                6 -> FIRST
                5 -> checkMatchBonus(matchBonus)
                4 -> FOURTH
                3 -> FIFTH
                else -> NONE
            }
        }

        private fun checkMatchBonus(matchBonus: Boolean): Rank {
            if (matchBonus) return SECOND
            return THIRD
        }
    }
}

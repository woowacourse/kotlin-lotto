package lotto.domain

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000) {
        override fun isSame(countOfMatch: Int, matchBonus: Boolean): Boolean {
            return super.isSame(countOfMatch, matchBonus) and matchBonus
        }
    },
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0) {
        override fun isSame(countOfMatch: Int, matchBonus: Boolean): Boolean {
            return (countOfMatch < MISS_RANGE_MAX) or (countOfMatch > WRONG_COUNT_RANGE)
        }
    };

    open fun isSame(countOfMatch: Int, matchBonus: Boolean): Boolean {
        return this.countOfMatch == countOfMatch
    }

    companion object {
        private const val MISS_RANGE_MAX = 3
        private const val WRONG_COUNT_RANGE = 6

        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return values().find {
                it.isSame(countOfMatch, matchBonus)
            } ?: MISS
        }
    }
}

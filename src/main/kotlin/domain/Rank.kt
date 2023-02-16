package domain

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000) {
        override fun toString(): String {
            return RANK_BONUS_MESSAGE.format(countOfMatch, winningMoney)
        }
    },
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    override fun toString(): String {
        return RANK_MESSAGE.format(countOfMatch, winningMoney)
    }

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank? {
            var rank = values().find {
                it.countOfMatch == countOfMatch
            }

            if (rank in listOf(SECOND, THIRD)) {
                rank = if (matchBonus) SECOND else THIRD
            }
            return rank
        }
        const val RANK_MESSAGE = "%d개 일치 (%d원)- "
        const val RANK_BONUS_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원)- "
    }
}

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return values().find { rank ->
                checkRankCondition(countOfMatch, matchBonus, rank)
            } ?: throw IllegalStateException(RANK_ERROR_MESSAGE)
        }

        private fun checkRankCondition(countOfMatch: Int, matchBonus: Boolean, rank: Rank): Boolean {
            if (countOfMatch == SECOND.countOfMatch && !matchBonus) return rank == THIRD
            if (countOfMatch < FIFTH.countOfMatch) return rank == MISS
            return rank.countOfMatch == countOfMatch
        }

        private const val RANK_ERROR_MESSAGE = "[ERROR] 등수를 계산할 수 없습니다!!"
    }
}

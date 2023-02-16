package domain

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
            require(countOfMatch in 0..6) { ERROR_COUNT_OF_MATCH_RANGE.format(countOfMatch) }
            require(countOfMatch != 6 || matchBonus.not()) { ERROR_IMPOSSIBLE_CASE }

            var rank = values().find { countOfMatch == it.countOfMatch } ?: MISS
            if (rank in listOf(SECOND, THIRD)) {
                rank = if (matchBonus) SECOND else THIRD
            }
            return rank
        }

        private const val ERROR_COUNT_OF_MATCH_RANGE = "맞는 숫자의 개수는 0 이상 6 이하여야 합니다.\n잘못된 값: %d"
        private const val ERROR_IMPOSSIBLE_CASE = "숫자가 6개 맞으면 보너스 번호가 매치될 수 없습니다."
    }
}

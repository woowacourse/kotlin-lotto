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
            require(countOfMatch in MIN_COUNT_OF_MATCH..MAX_COUNT_OF_MATCH) {
                ERROR_COUNT_OF_MATCH_RANGE.format(
                    countOfMatch,
                )
            }
            require(countOfMatch != MAX_COUNT_OF_MATCH || matchBonus.not()) { ERROR_IMPOSSIBLE_CASE }

            return when (countOfMatch) {
                6 -> FIRST
                5 -> if (matchBonus) SECOND else THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> MISS
            }
        }

        private const val MIN_COUNT_OF_MATCH = 0
        private const val MAX_COUNT_OF_MATCH = 6

        private const val ERROR_COUNT_OF_MATCH_RANGE =
            "맞는 숫자의 개수는 $MIN_COUNT_OF_MATCH 이상 $MAX_COUNT_OF_MATCH 이하여야 합니다.\n잘못된 값: %d"
        private const val ERROR_IMPOSSIBLE_CASE = "숫자가 ${MAX_COUNT_OF_MATCH}개 맞으면 보너스 번호가 매치될 수 없습니다."
    }
}

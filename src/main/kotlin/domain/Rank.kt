package domain

enum class Rank(val countOfMatch: Int, val mustHaveBonus: Boolean, val winningMoney: Int) {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0),
    ;

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            val min = values().minOf { it.countOfMatch }
            val max = values().maxOf { it.countOfMatch }
            require(countOfMatch in min..max) {
                ERROR_COUNT_OF_MATCH_RANGE.format(
                    countOfMatch,
                )
            }
            require(countOfMatch != max || matchBonus.not()) { ERROR_IMPOSSIBLE_CASE }

            val matchCount = values().filter { it.countOfMatch == countOfMatch }
            if (matchCount.size > 1) return matchCount.filter { it.mustHaveBonus == matchBonus }[0]
            if (matchCount.isEmpty()) return MISS
            return matchCount[0]
        }

        private const val ERROR_COUNT_OF_MATCH_RANGE = "맞는 숫자의 개수는 0 이상 6 이하여야 합니다.\n잘못된 값: %d"
        private const val ERROR_IMPOSSIBLE_CASE = "숫자가 6개 맞으면 보너스 번호가 매치될 수 없습니다."
    }
}

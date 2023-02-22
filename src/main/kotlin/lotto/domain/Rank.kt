package lotto.domain

enum class Rank(val matchCount: Int, val prizeMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NOTHING(-1, 0),
    ;

    companion object {
        fun convertToRank(matchCount: Int, bonusMatch: Boolean): Rank {
            if (matchCount == SECOND.matchCount) {
                if (bonusMatch) return SECOND
                return THIRD
            }

            return Rank.values().find { it.matchCount == matchCount }
                ?: NOTHING
        }
    }
}

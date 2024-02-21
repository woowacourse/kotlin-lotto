package lotto.model

enum class WinningRank(val matchCount: Int, val prize: Long, val isBonus: Boolean = false) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    companion object {
        fun findByMatchCount(matchCount: Int, isBonusMatched: Boolean): WinningRank {
            return entries.firstOrNull {
                it.matchCount == matchCount && (!it.isBonus || isBonusMatched)
            } ?: NONE
        }
    }
}

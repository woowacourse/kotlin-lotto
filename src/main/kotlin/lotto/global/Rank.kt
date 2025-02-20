package lotto.global

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun getRank(
            countOfMatch: Int,
            isBonusMatched: Boolean,
        ): Rank {
            if (countOfMatch == 5) {
                if (isBonusMatched) return SECOND
                return THIRD
            }
            val result = entries.toTypedArray().find { it.countOfMatch == countOfMatch }
            if (result == null) return MISS
            return result
        }
    }
}

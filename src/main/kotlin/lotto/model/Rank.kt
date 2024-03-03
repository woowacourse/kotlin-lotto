package lotto.model

private const val MATCH_COUNT_FIRST = 6
private const val MATCH_COUNT_SECOND = 5
private const val MATCH_COUNT_THIRD = 5
private const val MATCH_COUNT_FOURTH = 4
private const val MATCH_COUNT_FIFTH = 3
private const val MATCH_COUNT_MISS = 0

enum class Rank(val countOfMatch: Int, val winningAmount: Int) {
    FIRST(MATCH_COUNT_FIRST, 2_000_000_000),
    SECOND(MATCH_COUNT_SECOND, 30_000_000),
    THIRD(MATCH_COUNT_THIRD, 1_500_000),
    FOURTH(MATCH_COUNT_FOURTH, 50_000),
    FIFTH(MATCH_COUNT_FIFTH, 5_000),
    MISS(MATCH_COUNT_MISS, 0),
    ;

    companion object {
        fun getRank(
            countOfMatch: Int,
            bonusMatched: Boolean,
        ): Rank {
            return enumValues<Rank>().firstOrNull {
                it.countOfMatch == countOfMatch && (it != SECOND || bonusMatched)
            } ?: MISS
        }

        fun getRankByOrdinal(ordinal: Int): Rank {
            return entries.get(ordinal)
        }
    }
}

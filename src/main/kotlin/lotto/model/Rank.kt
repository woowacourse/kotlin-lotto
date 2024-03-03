package lotto.model

import lotto.util.MoneyConstants

private const val MATCH_COUNT_FIRST = 6
private const val MATCH_COUNT_SECOND = 5
private const val MATCH_COUNT_THIRD = 5
private const val MATCH_COUNT_FOURTH = 4
private const val MATCH_COUNT_FIFTH = 3
private const val MATCH_COUNT_MISS = 0

enum class Rank(val countOfMatch: Int, val winningAmount: Int) {
    FIRST(MATCH_COUNT_FIRST, MoneyConstants.FIRST_RANK_WINNING_MONEY),
    SECOND(MATCH_COUNT_SECOND, MoneyConstants.SECOND_RANK_WINNING_MONEY),
    THIRD(MATCH_COUNT_THIRD, MoneyConstants.THIRD_RANK_WINNING_MONEY),
    FOURTH(MATCH_COUNT_FOURTH, MoneyConstants.FOURTH_RANK_WINNING_MONEY),
    FIFTH(MATCH_COUNT_FIFTH, MoneyConstants.FIFTH_RANK_WINNING_MONEY),
    MISS(MATCH_COUNT_MISS, MoneyConstants.MISS_WINNING_MONEY),
    ;

    companion object {
        fun getRank(
            countOfMatch: Int,
            bonusMatched: Boolean,
        ): Rank {
            return when (countOfMatch) {
                MATCH_COUNT_FIRST -> FIRST
                MATCH_COUNT_SECOND -> SECOND.takeIf { bonusMatched } ?: THIRD
                MATCH_COUNT_FOURTH -> FOURTH
                MATCH_COUNT_FIFTH -> FIFTH
                else -> MISS
            }
        }

        fun getRankByOrdinal(ordinal: Int): Rank {
            return entries.get(ordinal)
        }
    }
}

package model

import util.Constant

enum class Rank(val countOfMatch: Int, val winningAmount: Int) {
    FIRST(6, Constant.FIRST_RANK_WINNING_MONEY),
    SECOND(5, Constant.SECOND_RANK_WINNING_MONEY),
    THIRD(5, Constant.THIRD_RANK_WINNING_MONEY),
    FOURTH(4, Constant.FOURTH_RANK_WINNING_MONEY),
    FIFTH(3, Constant.FIFTH_RANK_WINNING_MONEY),
    MISS(0, Constant.MISS_WINNING_MONEY),
    ;

    companion object {
        fun getRank(
            countOfMatch: Int,
            bonusMatched: Boolean,
        ): Rank {
            return when (countOfMatch) {
                6 -> FIRST
                5 -> SECOND.takeIf { bonusMatched } ?: THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> MISS
            }
        }

        fun getRankByOrdinal(ordinal: Int): Rank {
            return entries.get(ordinal)
        }
    }
}

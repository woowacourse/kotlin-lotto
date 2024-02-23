package lotto.model

import lotto.model.RankState.BONUS_WINNING
import lotto.model.RankState.DOES_NOT_WINNING
import lotto.model.RankState.WINNING

enum class Rank(val countOfMatch: Int, val winningMoney: Int, val rankState: RankState) {
    FIRST(6, 2_000_000_000, WINNING),
    SECOND(5, 30_000_000, BONUS_WINNING),
    THIRD(5, 1_500_000, WINNING),
    FOURTH(4, 50_000, WINNING),
    FIFTH(3, 5_000, WINNING),
    MISS(0, 0, DOES_NOT_WINNING),
    ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            rankState: RankState,
        ): Rank {
            return entries.find {
                it.countOfMatch == countOfMatch && it.rankState == rankState
            } ?: MISS
        }
    }
}

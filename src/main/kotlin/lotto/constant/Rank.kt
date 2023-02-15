package lotto.constant

import lotto.constant.BonusResult.ANY
import lotto.constant.BonusResult.BONUS_MATCH
import lotto.constant.BonusResult.BONUS_MISMATCH

enum class Rank(val matchCount: Int, val bonusMatch: BonusResult, val prizeMoney: Int) {
    FIRST(6, ANY, 2000000000),
    SECOND(5, BONUS_MATCH, 30000000),
    THIRD(5, BONUS_MISMATCH, 1500000),
    FOURTH(4, ANY, 50000),
    FIFTH(3, ANY, 5000),
    NOTHING(-1, ANY, 0),
    ;

    companion object {
        fun convertToGrade(matchCount: Int, bonusMatch: BonusResult): Rank =
            Rank.values().find { it.matchCount == matchCount && it.bonusMatch == bonusMatch }
                ?: throw IllegalStateException(WRONG_MATCH_COUNT_ERROR)

        fun convertToPrizeMoney(rank: Rank): Int =
            Rank.values().find { it == rank }?.prizeMoney ?: throw IllegalStateException(WRONG_RANK_ERROR)

        private const val WRONG_MATCH_COUNT_ERROR = "matchCount 의 값이 적절하지 못합니다."
        private const val WRONG_RANK_ERROR = "주어진 Rank 값이 올바르지 않습니다."
    }
}

enum class BonusResult {
    ANY, BONUS_MATCH, BONUS_MISMATCH
}

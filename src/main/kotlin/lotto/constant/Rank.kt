package lotto.constant

import lotto.constant.BonusResult.ANY
import lotto.constant.BonusResult.BONUS_MATCH
import lotto.constant.BonusResult.BONUS_MISMATCH

enum class Rank(val matchCount: Int, val bonusResult: BonusResult, val prizeMoney: Int) {
    FIRST(6, ANY, 2_000_000_000),
    SECOND(5, BONUS_MATCH, 30_000_000),
    THIRD(5, BONUS_MISMATCH, 1_500_000),
    FOURTH(4, ANY, 50_000),
    FIFTH(3, ANY, 5_000),
    NOTHING(-1, ANY, 0),
    ;

    companion object {
        fun convertToGrade(matchCount: Int, bonusResult: BonusResult): Rank {
            return Rank.values()
                .find { rank -> rank.matchCount == matchCount && (rank.bonusResult == bonusResult || rank.bonusResult == ANY) }
                ?: NOTHING
        }

        fun convertToPrizeMoney(rank: Rank): Int =
            Rank.values().find { it == rank }?.prizeMoney ?: throw IllegalStateException(WRONG_RANK_ERROR)

        private const val WRONG_RANK_ERROR = "주어진 Rank 값이 올바르지 않습니다."
    }
}

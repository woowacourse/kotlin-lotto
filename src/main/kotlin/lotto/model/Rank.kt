package lotto.model

import lotto.entity.Money

enum class Rank(val countOfMatch: Int, val winningMoney: Money) {
    FIRST(6, Money(2_000_000_000)),
    SECOND(5, Money(30_000_000)),
    THIRD(5, Money(1_500_000)),
    FOURTH(4, Money(50_000)),
    FIFTH(3, Money(5_000)),
    MISS(0, Money(0));

    private fun determine(countOfMatch: Int, isMatchBonus: Boolean): Boolean {
        if (this == SECOND && !isMatchBonus)
            return false
        if (this == MISS && countOfMatch in (0..2))
            return true
        return this.countOfMatch == countOfMatch
    }

    companion object {
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수 판별에 실패하였습니다."

        fun determineAll(countOfMatch: Int, isMatchBonus: Boolean): Rank {
            val determinedRank = Rank.values().find { it.determine(countOfMatch, isMatchBonus) }
            checkNotNull(determinedRank) { ERROR_DETERMINED_RANK_IS_EMPTY }
            return determinedRank
        }
    }
}

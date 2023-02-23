package lotto.model

import lotto.entity.Money

enum class Rank(val countOfMatch: Int, val winningMoney: Money) {
    FIRST(6, Money(2_000_000_000)),
    SECOND(5, Money(30_000_000)) {
        override fun determine(countOfMatch: Int, isMatchBonus: Boolean): Boolean {
            return super.determine(countOfMatch, isMatchBonus) && isMatchBonus
        }
    },
    THIRD(5, Money(1_500_000)),
    FOURTH(4, Money(50_000)),
    FIFTH(3, Money(5_000)),
    MISS(0, Money(0)) {
        override fun determine(countOfMatch: Int, isMatchBonus: Boolean): Boolean {
            return countOfMatch in (0..2)
        }
    };

    open fun determine(countOfMatch: Int, isMatchBonus: Boolean): Boolean {
        return this.countOfMatch == countOfMatch
    }

    companion object {
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수 판별에 실패하였습니다."

        fun determineAll(countOfMatch: Int, isMatchBonus: Boolean): Rank {
            val determinedRank = Rank.values().find { it.determine(countOfMatch, isMatchBonus) }
            check(determinedRank != null) {
                ERROR_DETERMINED_RANK_IS_EMPTY
            }
            return determinedRank
        }
    }
}

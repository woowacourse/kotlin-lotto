package lotto.model

import lotto.entity.Lotto
import lotto.entity.Money
import lotto.entity.WinLotto

enum class Rank(val countOfMatch: Int, val needBonus: Boolean, val winningMoney: Money) {
    FIRST(6, false, Money(2_000_000_000)),
    SECOND(5, true, Money(30_000_000)),
    THIRD(5, false, Money(1_500_000)),
    FOURTH(4, false, Money(50_000)),
    FIFTH(3, false, Money(5_000)),
    MISS(0, false, Money(0));

    companion object {
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수 판별에 실패하였습니다."

        fun determine(lotto: Lotto, winLotto: WinLotto): Rank {
            val determinedRank = Rank.values().filter {
                lotto.determineRank(it, winLotto)
            }
            check(determinedRank.isNotEmpty()) {
                String.format(ERROR_DETERMINED_RANK_IS_EMPTY)
            }
            return determinedRank[0]
        }
    }
}

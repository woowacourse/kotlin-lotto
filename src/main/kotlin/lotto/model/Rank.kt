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
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수는 반드시 하나 판별되어야 합니다. 판별된 등수 개수 : %d"

        fun determine(lotto: Lotto, winLotto: WinLotto): Rank {
            val determinedRank = Rank.values().filter {
                lotto.determineRank(it, winLotto)
            }
            check(determinedRank.size == 1) {
                String.format(ERROR_DETERMINED_RANK_IS_EMPTY, determinedRank.size)
            }
            return determinedRank[0]
        }
    }
}

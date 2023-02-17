package lotto.model

import lotto.entity.Lotto
import lotto.entity.WinLotto

enum class Rank(val countOfMatch: Int, val needBonus: Boolean, val winningMoney: Int) {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    companion object {
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수는 반드시 하나 판별되어야 합니다. 판별된 등수 개수 : %d"

        fun determine(lotto: Lotto, winLotto: WinLotto): Rank {
            val determinedRank = Rank.values().filter {
                determineSingle(it, lotto, winLotto)
            }
            check(determinedRank.size == 1) {
                String.format(ERROR_DETERMINED_RANK_IS_EMPTY, determinedRank.size)
            }
            return determinedRank[0]
        }

        private fun determineSingle(condition: Rank, lotto: Lotto, winLotto: WinLotto): Boolean {
            val countOfMatch = lotto.matchLottoNumberCount(winLotto.winNumber)
            var isMatch = countOfMatch == condition.countOfMatch
            if (condition == MISS && countOfMatch in 0..2)
                return true
            if (condition.needBonus && !lotto.isMatchBonus(winLotto.bonus)) {
                isMatch = false
            }
            return isMatch
        }
    }
}

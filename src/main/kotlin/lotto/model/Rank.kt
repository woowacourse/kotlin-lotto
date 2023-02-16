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
        fun determine(lotto: Lotto, winLotto: WinLotto): Rank {
            return Rank.values().filter {
                determineSingle(it, lotto, winLotto)
            }[0]
        }

        private fun determineSingle(condition: Rank, lotto: Lotto, winLotto: WinLotto): Boolean {
            var match = lotto.matchLottoNumberCount(winLotto.winNumber) == condition.countOfMatch
            if (condition.needBonus && !lotto.isMatchBonus(winLotto.bonus)) {
                match = false
            }
            return match
        }
    }
}

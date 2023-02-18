package domain

import model.Lotto
import model.LottoNumber
import model.WinningLotto

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        fun getRank(lotto: Lotto, winningLotto: WinningLotto): Rank? {
            val countOfMatch = getCountOfMatch(lotto, winningLotto.lotto)
            val matchBonus = isBonusMatch(lotto, winningLotto.bonusNumber)
            return Rank.valueOf(countOfMatch, matchBonus)
        }

        fun getCountOfMatch(lotto: Lotto, winningLotto: Lotto): Int {
            return lotto.lottoNumbers.count { lottoNumber ->
                winningLotto.lottoNumbers.contains(lottoNumber)
            }
        }

        fun isBonusMatch(lotto: Lotto, bonusNumber: LottoNumber): Boolean {
            return lotto.lottoNumbers.contains(bonusNumber)
        }

        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank? {
            return values().find { rank ->
                checkRankCondition(countOfMatch, matchBonus, rank)
            }
        }

        private fun checkRankCondition(countOfMatch: Int, matchBonus: Boolean, rank: Rank): Boolean {
            if (countOfMatch == SECOND.countOfMatch && !matchBonus) return rank == THIRD
            if (countOfMatch < FIFTH.countOfMatch) return rank == MISS
            return rank.countOfMatch == countOfMatch
        }
    }
}

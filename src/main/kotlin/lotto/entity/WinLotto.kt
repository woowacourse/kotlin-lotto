package lotto.entity

import lotto.model.Rank

class WinLotto(private val winNumber: Lotto, private val bonus: LottoNumber) {
    init {
        require(!winNumber.numbers.contains(bonus)) { ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER.format(winNumber.toString(), bonus.value) }
    }

    fun determineRank(lotto: Lotto): Rank {
        val countOfMatch = lotto.matchLottoNumberCount(winNumber)
        val isMatchBonus = lotto.isMatchBonus(bonus)
        val determinedRank = Rank.values().filter { determineSingleRank(it, countOfMatch, isMatchBonus) }
        check(determinedRank.isNotEmpty()) {
            String.format(ERROR_DETERMINED_RANK_IS_EMPTY)
        }
        return determinedRank[0]
    }

    private fun determineSingleRank(rank: Rank, countOfMatch: Int, isMatchBonus: Boolean): Boolean {
        if (rank == Rank.MISS && countOfMatch in 0..2) return true
        if (rank == Rank.SECOND && !isMatchBonus) return false
        return rank.countOfMatch == countOfMatch
    }

    companion object {
        const val ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER = "보너스 번호와 당첨 번호는 중복될 수 없습니다. 입력된 당첨 번호 : %s, 보너스 번호 : %d"
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수 판별에 실패하였습니다."
    }
}

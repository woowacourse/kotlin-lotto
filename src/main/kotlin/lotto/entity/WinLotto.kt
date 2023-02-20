package lotto.entity

import lotto.model.Rank

class WinLotto(private val winNumber: Lotto, private val bonus: LottoNumber) {
    init {
        require(!winNumber.numbers.contains(bonus)) {
            String.format(
                ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER,
                winNumber.toString(),
                bonus.value
            )
        }
    }

    fun determineLottoRank(condition: Rank, lotto: Lotto): Boolean {
        val countOfMatch = lotto.matchLottoNumberCount(winNumber)
        if (condition == Rank.MISS && countOfMatch in 0..2)
            return true
        if (condition.needBonus && !lotto.isMatchBonus(bonus))
            return false
        return countOfMatch == condition.countOfMatch
    }

    companion object {
        const val ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER =
            "보너스 번호와 당첨 번호는 중복될 수 없습니다. 입력된 당첨 번호 : %s, 보너스 번호 : %d"
    }
}

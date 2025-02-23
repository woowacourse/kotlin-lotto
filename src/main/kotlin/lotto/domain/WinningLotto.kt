package lotto.domain

import lotto.util.Rank

class WinningLotto(private val winningLottoNumber: Lotto, private val winningBonusNumber: LottoNumber) {
    init {
        validateNotDuplicationNumber()
    }

    fun match(lotto: Lotto): Rank {
        val matchedLotto: Int = lotto.numbers.intersect(winningLottoNumber.numbers.toSet()).size
        val isMatchedBonus: Boolean = lotto.numbers.contains(winningBonusNumber)
        return Rank.getRankState(matchedLotto, isMatchedBonus)
    }

    fun validateNotDuplicationNumber() {
        require(winningLottoNumber.numbers.all { it != winningBonusNumber }) { ERROR_NOT_DUPLICATE_BONUS_NUMBER }
    }

    companion object {
        const val ERROR_NOT_DUPLICATE_BONUS_NUMBER = "[ERROR] 보너스 번호는 로또 번호와 중복될 수 없습니다."
    }
}

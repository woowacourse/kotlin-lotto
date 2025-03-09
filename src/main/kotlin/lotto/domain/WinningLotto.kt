package lotto.domain

import lotto.util.Rank

class WinningLotto private constructor(private val winningLottoNumber: Lotto, private val winningBonusNumber: LottoNumber) {
    companion object {
        const val ERROR_NOT_DUPLICATE_BONUS_NUMBER = "[ERROR] 로또 번호와 보너스 번호는 중복될 수 없습니다. 당첨 번호를 다시 입력해주세요."

        fun of(
            winningLotto: Lotto,
            winningBonusNumber: LottoNumber,
        ): WinningLotto {
            if (winningLotto.lotto.contains(winningBonusNumber)) {
                throw IllegalArgumentException(ERROR_NOT_DUPLICATE_BONUS_NUMBER)
            }
            return WinningLotto(winningLotto, winningBonusNumber)
        }
    }

    fun match(lotto: Lotto): Rank {
        val matchedLotto: Int = lotto.lotto.intersect(winningLottoNumber.lotto.toSet()).size
        val isMatchedBonus: Boolean = lotto.lotto.contains(winningBonusNumber)
        return Rank.getRankState(matchedLotto, isMatchedBonus)
    }
}

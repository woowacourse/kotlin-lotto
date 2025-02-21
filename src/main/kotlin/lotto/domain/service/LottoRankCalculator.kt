package lotto.domain.service

import lotto.domain.model.BonusNumber
import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank
import lotto.domain.model.WinningNumbers

class LottoRankCalculator {
    fun calculate(
        lotto: Lotto,
        winningNumbers: WinningNumbers,
        bonusNumber: BonusNumber,
    ): LottoRank {
        val matchCount = getMatchCount(lotto, winningNumbers)
        val isMatchBonusNumber = hasBonusNumber(lotto, bonusNumber)
        return LottoRank.calculate(matchCount, isMatchBonusNumber)
    }

    private fun getMatchCount(
        lotto: Lotto,
        winningNumbers: WinningNumbers,
    ): Int {
        return lotto.numbers.count { number -> winningNumbers.numbers.contains(number) }
    }

    private fun hasBonusNumber(
        lotto: Lotto,
        bonusNumber: BonusNumber,
    ): Boolean = lotto.numbers.contains(bonusNumber.number)
}

package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank
import lotto.domain.model.WinningNumbers

class LottoRankCalculator {
    fun calculate(
        lotto: Lotto,
        winningNumbers: WinningNumbers,
    ): LottoRank {
        val matchCount = winningNumbers.getMatchCount(lotto)
        val isMatchBonusNumber = winningNumbers.hasBonusNumber(lotto)
        return LottoRank.calculate(matchCount, isMatchBonusNumber)
    }
}

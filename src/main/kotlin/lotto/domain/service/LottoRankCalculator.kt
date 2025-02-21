package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoRank
import lotto.domain.model.LottoRanks
import lotto.domain.model.WinningNumbers

class LottoRankCalculator {
    fun calculate(
        lottos: List<Lotto>,
        winningNumbers: WinningNumbers,
    ): LottoRanks {
        return LottoRanks(lottos.map { lotto -> calculateLottoRank(lotto, winningNumbers) })
    }

    private fun calculateLottoRank(
        lotto: Lotto,
        winningNumbers: WinningNumbers,
    ): LottoRank {
        val matchCount = winningNumbers.getMatchCount(lotto)
        val isMatchBonusNumber = winningNumbers.hasBonusNumber(lotto)
        return LottoRank.calculate(matchCount, isMatchBonusNumber)
    }
}

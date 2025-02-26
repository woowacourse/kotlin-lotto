package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoResult
import lotto.domain.model.WinningLotto
import lotto.enums.Rank

class LottoCalculator {
    fun calculate(
        winningLotto: WinningLotto,
        lottos: List<Lotto>,
    ): LottoResult {
        val winningStats = getWinningStats(winningLotto, lottos)
        return LottoResult(winningStats)
    }

    private fun getWinningStats(
        winningLotto: WinningLotto,
        lottos: List<Lotto>,
    ): Map<Rank, Int> = lottos.groupingBy { winningLotto.getRank(it) }.eachCount()
}

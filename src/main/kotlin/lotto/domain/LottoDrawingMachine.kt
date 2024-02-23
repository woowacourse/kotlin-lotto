package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto

class LottoDrawingMachine {

    fun countRank(lottoTickets: List<Lotto>, winningLotto: WinningLotto): LottoDrawingResult {
        val rankCountMap = Rank.entries.associateWith { DEFAULT_COUNT }.toMutableMap()
        lottoTickets.forEach { targetLotto ->
            val rank = getRank(targetLotto, winningLotto)
            rankCountMap[rank] = rankCountMap.getOrDefault(rank, DEFAULT_COUNT) + COUNT_STEP
        }
        return LottoDrawingResult(rankCountMap)
    }

    private fun getRank(targetLotto: Lotto, winningLotto: WinningLotto): Rank {
        val countOfMatch = matchCount(targetLotto, winningLotto.lotto)
        val matchBonus = bonusCount(targetLotto, winningLotto.bonus)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun matchCount(targetLotto: Lotto, winningLotto: Lotto): Int {
        return (targetLotto.numbers.intersect(winningLotto.numbers.toSet())).size
    }

    private fun bonusCount(targetLotto: Lotto, bonusNumber: LottoNumber): Boolean {
        return targetLotto.numbers.contains(bonusNumber)
    }

    companion object {
        private const val DEFAULT_COUNT = 0
        private const val COUNT_STEP = 1
    }
}

package domain

import domain.model.Lotto
import domain.model.LottoDrawingResult
import domain.model.LottoNumber
import domain.model.Rank
import domain.model.WinningLotto

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

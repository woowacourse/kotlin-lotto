package domain

import domain.model.Lotto
import domain.model.LottoDrawingResult
import domain.model.LottoNumber
import domain.model.Rank
import domain.model.WinningLotto

class LottoDrawingMachine {

    fun countRank(lottoTickets: List<Lotto>, winningLotto: WinningLotto): LottoDrawingResult {
        val rankCountMap = Rank.entries.take(5).associateWith { 0 }.toMutableMap()
        lottoTickets.forEach { targetLotto ->
            val rank = getRank(targetLotto, winningLotto)
            if (rank != Rank.MISS) {
                rankCountMap[rank] = rankCountMap.getOrDefault(rank, 0) + 1
            }
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
}

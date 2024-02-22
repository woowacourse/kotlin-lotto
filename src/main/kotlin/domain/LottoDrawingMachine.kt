package domain

import domain.model.Lotto
import domain.model.LottoDrawingResult
import domain.model.LottoNumber
import domain.model.Rank

class LottoDrawingMachine {

    fun countRank(lottoTickets: List<Lotto>, winningLotto: Lotto, bonusNumber: LottoNumber): LottoDrawingResult {
        val rankCountMap = Rank.entries.associateWith { 0 }.toMutableMap()
        lottoTickets.forEach { targetLotto ->
            val rank = getRank(targetLotto, winningLotto, bonusNumber)
            rankCountMap[rank] = rankCountMap.getOrDefault(rank, 0) + 1
        }
        return LottoDrawingResult(rankCountMap)
    }

    private fun getRank(targetLotto: Lotto, winningLotto: Lotto, bonusNumber: LottoNumber): Rank {
        val countOfMatch = matchCount(targetLotto, winningLotto)
        val matchBonus = bonusCount(targetLotto, bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun matchCount(targetLotto: Lotto, winningLotto: Lotto): Int {
        return (targetLotto.numbers.intersect(winningLotto.numbers.toSet())).size
    }

    private fun bonusCount(targetLotto: Lotto, bonusNumber: LottoNumber): Boolean {
        return targetLotto.numbers.contains(bonusNumber)
    }
}

package lotto.service

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import lotto.domain.WinningLottoTicket

object LottoRankFinder {
    fun findLottoRanks(
        manyLotto: List<Lotto>,
        winningLottoTicket: WinningLottoTicket,
    ): ScoreRankMap {
        val rankMap = Rank.entries.associateWith { 0 }.toMutableMap()
        for (lotto in manyLotto) {
            val rank = findLottoRank(lotto, winningLottoTicket)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return ScoreRankMap(rankMap.toMap())
    }

    fun findLottoRank(
        lotto: Lotto,
        winningLottoTicket: WinningLottoTicket,
    ): Rank {
        val countOfMatch = winningLottoTicket.getCountOfMatchWith(lotto)
        val isBonusMatched = winningLottoTicket.isMatchedBonusWith(lotto)
        return Rank.getRank(countOfMatch, isBonusMatched)
    }
}

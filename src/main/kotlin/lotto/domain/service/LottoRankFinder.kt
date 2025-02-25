package lotto.domain.service

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap

class LottoRankFinder {
    fun findLottoRanks(
        manyLotto: List<Lotto>,
        winningLotto: Lotto,
        bonus: Int,
    ): ScoreRankMap {
        val rankMap = Rank.entries.associateWith { 0 }.toMutableMap()
        for (lotto in manyLotto) {
            val rank = findLottoRank(lotto, winningLotto, bonus)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return ScoreRankMap(rankMap.toMap())
    }

    fun findLottoRank(
        lotto: Lotto,
        winningLotto: Lotto,
        bonus: Int,
    ): Rank {
        val countOfMatch = winningLotto.getCountOfMatchWith(lotto)
        val isBonusMatched = winningLotto.contains(bonus) && !lotto.contains(bonus)
        return Rank.getRank(countOfMatch, isBonusMatched)
    }
}

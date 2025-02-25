package lotto

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import lotto.global.LottoUtil.getLottoRank

class LottoService {
    fun getLottoRankMany(
        manyLotto: List<Lotto>,
        winningLotto: Lotto,
        bonus: Int,
    ): ScoreRankMap {
        val rankMap = Rank.entries.associateWith { 0 }.toMutableMap()
        for (lotto in manyLotto) {
            val rank = getLottoRank(lotto, winningLotto, bonus)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return ScoreRankMap(rankMap.toMap())
    }
}

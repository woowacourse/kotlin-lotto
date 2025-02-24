package lotto

import lotto.domain.Lotto
import lotto.domain.ScoreRankMap
import lotto.global.LottoUtil.getLotto
import lotto.global.LottoUtil.getLottoRank
import lotto.global.Rank

class LottoService {
    fun getManyLotto(iterates: Int): List<Lotto> {
        val manyLotto = mutableListOf<Lotto>()
        repeat(iterates) { manyLotto.add(getLotto()) }
        return manyLotto.toList()
    }

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

    companion object {
        fun getRate(scoreRankMap: ScoreRankMap): String {
            val earned = scoreRankMap.getEarned()
            val paid = scoreRankMap.getPaid()
            return if (paid == 0) "0.0" else String.format("%.2f", earned.toDouble() / paid.toDouble())
        }
    }
}

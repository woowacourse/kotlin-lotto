package lotto.global

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap

object LottoUtil {
    fun getLottoRank(
        lotto: Lotto,
        winningLotto: Lotto,
        bonus: Int,
    ): Rank {
        val countOfMatch = winningLotto.getCountOfMatchWith(lotto)
        val isBonusMatched = winningLotto.contains(bonus) && !lotto.contains(bonus)
        return Rank.getRank(countOfMatch, isBonusMatched)
    }

    fun getRate(scoreRankMap: ScoreRankMap): String {
        val earned = scoreRankMap.getEarned()
        val paid = scoreRankMap.getPaid()
        return if (paid == 0) "0.0" else String.format("%.2f", earned.toDouble() / paid.toDouble())
    }
}

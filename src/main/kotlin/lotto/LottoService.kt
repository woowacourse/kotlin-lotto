package lotto

import lotto.domain.Lotto
import lotto.domain.ScoreRankMap
import lotto.global.Config.MAX_LOTTO_LENGTH
import lotto.global.Config.MAX_RANDOM_NUM
import lotto.global.Config.MIN_RANDOM_NUM
import lotto.global.Rank
import java.util.LinkedList

class LottoService {
    fun getLotto(): Lotto {
        // 애초에 이미 리스트를 만들고 래핑하는게 맞나? 이 로직을 lotto 내부로 숨겨야 할까?
        val lotto = mutableListOf<Int>()
        val range: LinkedList<Int> = LinkedList(IntRange(MIN_RANDOM_NUM, MAX_RANDOM_NUM).shuffled())
        repeat(MAX_LOTTO_LENGTH) { lotto.add(range.poll()) }

        return Lotto(lotto.toList())
    }

    fun getManyLotto(iterates: Int): List<Lotto> {
        val manyLotto = mutableListOf<Lotto>()
        repeat(iterates) { manyLotto.add(getLotto()) }
        return manyLotto.toList()
    }

    fun checkRank(
        lotto: Lotto,
        winningLotto: Lotto,
        bonus: Int,
    ): Rank {
        val countOfMatch = winningLotto.getCountOfMatchWith(lotto)
        val isBonusMatched = winningLotto.contains(bonus) && !lotto.contains(bonus)
        return Rank.getRank(countOfMatch, isBonusMatched)
    }

    fun checkRankMany(
        manyLotto: List<Lotto>,
        winningLotto: Lotto,
        bonus: Int,
    ): ScoreRankMap {
        val rankMap = Rank.entries.associateWith { 0 }.toMutableMap()
        for (lotto in manyLotto) {
            val rank = checkRank(lotto, winningLotto, bonus)
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

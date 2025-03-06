package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto

class WinningListMaker(
    private val winningLotto: WinningLotto,
) {
    fun makeWinningList(lottos: List<Lotto>): Map<Rank, Int> {
        val ranks = lottos.map { winningLotto.findRank(it) }
        val rankCounts =
            Rank.entries
                .associateWith { 0 }
                .toMutableMap()
        ranks.forEach {
            rankCounts[it] = rankCounts.getOrDefault(it, 0) + 1
        }
        return rankCounts.filter { it.key != Rank.MISS }.toSortedMap(compareByDescending { it.ordinal })
    }
}

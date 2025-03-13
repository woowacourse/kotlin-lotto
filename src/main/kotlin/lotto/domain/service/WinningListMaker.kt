package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank

class WinningListMaker(
    private val winningLotto: Lotto,
    private val winningLottoNumber: LottoNumber,
) {
    fun makeWinningList(lottos: List<Lotto>): Map<Rank, Int> {
        val ranks = lottos.map { Rank.valueOf(it.countMatchNumbers(winningLotto), it.findNumber(winningLottoNumber)) }
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

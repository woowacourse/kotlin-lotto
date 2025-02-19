package domain

import Rank

class LottoWinningStats(
    private val winningLotto: WinningLotto,
    private val lottos: List<Lotto>,
) {
    private val winningStats: MutableMap<Rank, Int> =
        Rank.entries.associateWith { 0 }.toMutableMap()

    fun getLottoRanks(): Map<Rank, Int> {
        lottos.forEach {
            val rank = winningLotto.getRank(it)
            winningStats[rank] = (winningStats[rank]?.plus(1)) ?: 1
        }
        return winningStats
    }
}

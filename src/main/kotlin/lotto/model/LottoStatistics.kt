package lotto.model

class LottoStatistics(
    private val lottos: Lottos,
    private val winningLotto: WinningLotto,
) {
    private val _lottoStatistics = mutableMapOf<Rank, Int>()
    val lottoStatistics: Map<Rank, Int> get() = _lottoStatistics

    init {
        calculateStatistics()
    }

    private fun calculateStatistics() {
        lottos.lottoBundle.forEach { lotto ->
            val rank = winningLotto.findLottoRank(lotto)
            _lottoStatistics[rank] = _lottoStatistics.getOrDefault(rank, 0) + 1
        }
    }
}

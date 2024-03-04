package lotto.model

class LottoDrawingResult {
    private val _statistics: MutableMap<Rank, Int> = Rank.entries.associateWith { DEFAULT_COUNT }.toMutableMap()
    val statistics: Map<Rank, Int>
        get() = _statistics

    fun countRank(lottoTickets: List<Lotto>, winningLotto: WinningLotto) {
        lottoTickets.forEach { targetLotto ->
            val countOfMatch = winningLotto.countMatch(targetLotto)
            val matchBonus = winningLotto.matchBonus(targetLotto)
            val rank = Rank.valueOf(countOfMatch, matchBonus)

            _statistics.merge(rank, COUNT_STEP, Int::plus)
        }
    }

    fun calculateTotalPrize(): Prize {
        val totalAmount = _statistics.entries.sumOf { (rank, count) -> rank.winningMoney.toLong() * count }
        return Prize(totalAmount)
    }

    companion object {
        private const val DEFAULT_COUNT = 0
        private const val COUNT_STEP = 1
    }
}

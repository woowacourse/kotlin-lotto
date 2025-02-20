package lotto.model

class LottoStatistics(
    private val lottos: Lottos,
    private val winningLotto: WinningLotto,
    private val purchaseMoney: LottoPurchaseAmount,
) {
    private val _lottoStatistics = mutableMapOf<Rank, Int>()
    val lottoStatistics: Map<Rank, Int> get() = _lottoStatistics

    init {
        calculateStatistics()
    }

    private fun calculateStatistics() {
        lottos.lottoBundle.forEach { lotto ->
            val rank = winningLotto.findLottoRank(lotto)
            _lottoStatistics[rank] = _lottoStatistics.getOrDefault(rank, INITIAL_VALUE) + INCREMENT_COUNT_UNIT
        }
    }

    fun getRateOfReturn(): Double {
        val totalPrize = getTotalPrize()
        val rateOfReturn = totalPrize / purchaseMoney.money

        return rateOfReturn
    }

    private fun getTotalPrize(): Double {
        var sum = 0.0

        _lottoStatistics.forEach { (rank, count) ->
            sum += rank.prizeMoney * count
        }
        return sum
    }

    companion object {
        private const val INITIAL_VALUE = 0
        private const val INCREMENT_COUNT_UNIT = 1
    }
}

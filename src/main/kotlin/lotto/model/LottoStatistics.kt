package lotto.model

class LottoStatistics(
    private val lottos: Lottos,
    private val winningLotto: WinningLotto,
    private val purchaseMoney: LottoPurchaseAmount,
) {
    val lottoStatistics = calculateStatistics()

    private fun calculateStatistics(): Map<Rank, Int> {
        val lottoStatistics = mutableMapOf<Rank, Int>()
        lottos.lottoBundle.forEach { lotto ->
            val rank = winningLotto.findLottoRank(lotto)
            lottoStatistics[rank] = lottoStatistics.getOrDefault(rank, INITIAL_VALUE) + INCREMENT_COUNT_UNIT
        }

        return lottoStatistics.toMap()
    }

    fun getRateOfReturn(): Double {
        val totalPrize = getTotalPrize()
        val rateOfReturn = totalPrize / purchaseMoney.money

        return rateOfReturn
    }

    private fun getTotalPrize(): Double {
        var sum = 0.0
        lottoStatistics.forEach { (rank, count) ->
            sum += rank.prizeMoney * count
        }

        return sum
    }

    companion object {
        private const val INITIAL_VALUE = 0
        private const val INCREMENT_COUNT_UNIT = 1
    }
}

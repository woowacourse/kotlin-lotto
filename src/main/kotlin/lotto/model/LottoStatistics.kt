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
            lottoStatistics[rank] = lottoStatistics.getOrDefault(rank, INITIAL_LOTTO_RANK_COUNT) + INCREMENT_COUNT_UNIT
        }

        return lottoStatistics.toMap()
    }

    fun getRateOfReturn(): Double {
        val totalPrize = getTotalPrize()
        val rateOfReturn = totalPrize / purchaseMoney.money

        return rateOfReturn
    }

    private fun getTotalPrize(): Double {
        var sum = INITIAL_TOTAL_PRIZE
        lottoStatistics.forEach { (rank, count) ->
            sum += rank.prizeMoney * count
        }

        return sum
    }

    fun getIsLossMoney(): Boolean = getRateOfReturn() < 1

    companion object {
        private const val INITIAL_LOTTO_RANK_COUNT = 0
        private const val INCREMENT_COUNT_UNIT = 1
        private const val INITIAL_TOTAL_PRIZE = 0.0
    }
}

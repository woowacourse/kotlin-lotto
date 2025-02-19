package domain

import Rank
import constants.LottoConstants
import domain.value.EarningRate

class LottoWinningStats(
    private val winningLotto: WinningLotto,
    private val lottos: List<Lotto>,
) {
    val winningStats: MutableMap<Rank, Int> =
        Rank.entries.associateWith { 0 }.toMutableMap()

    init {
        initializeLottoRanks()
    }

    fun calculateEarningRate(): EarningRate {
        val purchaseAmount = lottos.size * LottoConstants.LOTTO_PRICE
        val winningAmount = calculateWinningAmount()
        val rate = winningAmount.toDouble() / purchaseAmount
        return EarningRate(rate)
    }

    private fun initializeLottoRanks() {
        lottos.forEach {
            val rank = winningLotto.getRank(it)
            winningStats[rank] = (winningStats[rank]?.plus(1)) ?: 1
        }
    }

    private fun calculateWinningAmount(): Int {
        var result = 0
        winningStats.forEach { (rank, count) ->
            result += rank.winningMoney * count
        }
        return result
    }
}

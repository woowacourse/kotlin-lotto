package domain

import Rank
import constants.LottoConstants
import domain.value.PurchaseAmount

class LottoCalculator(
    private val winningLotto: WinningLotto,
    private val lottos: List<Lotto>,
) {
    fun calculateWinningStats(): LottoWinningStats {
        val winningStats = getWinningStats()
        val purchaseAmount = getPurchaseAmount()
        return LottoWinningStats(winningStats, purchaseAmount)
    }

    private fun getWinningStats(): Map<Rank, Int> {
        val winningStats: MutableMap<Rank, Int> =
            Rank.entries.associateWith { 0 }.toMutableMap()
        lottos.forEach {
            val rank = winningLotto.getRank(it)
            winningStats[rank] = (winningStats[rank]?.plus(1)) ?: 1
        }
        return winningStats
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val amount = lottos.size * LottoConstants.LOTTO_PRICE
        return PurchaseAmount(amount)
    }
}

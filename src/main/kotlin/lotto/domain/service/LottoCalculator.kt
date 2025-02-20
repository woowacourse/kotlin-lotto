package lotto.domain.service

import lotto.constants.LottoConstants
import lotto.domain.model.Lotto
import lotto.domain.model.LottoWinningStats
import lotto.domain.model.WinningLotto
import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank

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

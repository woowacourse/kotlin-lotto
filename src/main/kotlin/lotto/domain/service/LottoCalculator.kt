package lotto.domain.service

import lotto.domain.model.LottoResult
import lotto.domain.model.PurchaseDetail
import lotto.domain.model.WinningLotto
import lotto.enums.Rank

class LottoCalculator {
    fun calculateLottoResult(
        winningLotto: WinningLotto,
        purchaseDetail: PurchaseDetail,
    ): LottoResult {
        val winningStats = getWinningStats(winningLotto, purchaseDetail)
        val purchaseAmount = purchaseDetail.purchaseAmount
        return LottoResult(winningStats, purchaseAmount)
    }

    private fun getWinningStats(
        winningLotto: WinningLotto,
        purchaseDetail: PurchaseDetail,
    ): Map<Rank, Int> {
        val winningStats: MutableMap<Rank, Int> =
            Rank.entries.associateWith { 0 }.toMutableMap()
        purchaseDetail.lottos.forEach {
            val rank = winningLotto.getRank(it)
            winningStats[rank] = (winningStats[rank]?.plus(1)) ?: 1
        }
        return winningStats
    }
}

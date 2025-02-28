package lotto.domain.model.winning

import lotto.domain.enums.Rank
import lotto.domain.model.LottoPayInfo
import lotto.domain.valueobject.EarningInfo

class LottoWinningStats(
    private val winningStatsInfo: Map<Rank, Int>,
    private val lottoPayInfo: LottoPayInfo,
) {
    fun getEarningInfo(): EarningInfo {
        val winningAmount = calculateWinningAmount()
        val rate = winningAmount.toDouble() / lottoPayInfo.lottoPurchaseAmount
        return EarningInfo(rate)
    }

    fun getWholeWinningStatsWithoutMiss(): Map<Rank, Int> {
        val emptyWinningStatus = Rank.entries.associateWith { 0 }
        val withMiss = emptyWinningStatus + winningStatsInfo
        return withMiss.filter { (key, _) -> key != Rank.MISS }
    }

    private fun calculateWinningAmount(): Int = winningStatsInfo.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}

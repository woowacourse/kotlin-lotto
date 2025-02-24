package lotto.domain.model

import lotto.domain.value.EarningInfo
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

class LottoWinningStats(
    private val winningStatsInfo: Map<Rank, Int>,
    private val lottoPayInfo: LottoPayInfo,
) {
    fun getEarningRate(): EarningInfo {
        val winningAmount = calculateWinningAmount()
        val rate = winningAmount.toDouble() / lottoPayInfo.lottoPurchaseAmount
        return EarningInfo(rate)
    }

    fun getWinningStatsWithEmptyWithoutMiss(): Map<Rank, Int> {
        val emptyWinningStatus = Rank.entries.associateWith { 0 }
        val withMiss = emptyWinningStatus + winningStatsInfo
        return withMiss.filter { (key, _) -> key != Rank.MISS }
    }

    private fun calculateWinningAmount(): Int = winningStatsInfo.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}

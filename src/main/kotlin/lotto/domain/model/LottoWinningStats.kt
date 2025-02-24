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

    fun getWinningStatsWithEmpty(): Map<Rank, Int> {
        val emptyWinningStatus = Rank.entries.associateWith { 0 }
        return emptyWinningStatus + winningStatsInfo
    }

    private fun calculateWinningAmount(): Int = winningStatsInfo.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}

package lotto.domain.model

import lotto.domain.value.EarningRate
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

class LottoWinningStats(
    val winningStats: Map<Rank, Int>,
    private val lottoPayInfo: LottoPayInfo,
) {
    fun getEarningRate(): EarningRate {
        val winningAmount = calculateWinningAmount()
        val rate = winningAmount.toDouble() / lottoPayInfo.lottoPurchaseAmount
        return EarningRate(rate)
    }

    private fun calculateWinningAmount(): Int = winningStats.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}

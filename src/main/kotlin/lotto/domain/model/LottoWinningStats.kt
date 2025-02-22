package lotto.domain.model

import lotto.domain.value.EarningRate
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

class LottoWinningStats(
    val winningStats: Map<Rank, Int>,
    private val lottoPayInfo: LottoPayInfo,
) {
    fun calculateEarningRate(): EarningRate {
        val winningAmount = calculateWinningAmount()
        val rate = winningAmount.toDouble() / lottoPayInfo.lottoPurchaseAmount
        return EarningRate(rate)
    }

    private fun calculateWinningAmount(): Int {
        var result = 0
        winningStats.forEach { (rank, count) ->
            result += rank.winningMoney * count
        }
        return result
    }
}

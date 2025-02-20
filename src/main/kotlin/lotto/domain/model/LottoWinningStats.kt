package lotto.domain.model

import lotto.domain.value.EarningRate
import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank

class LottoWinningStats(
    val winningStats: Map<Rank, Int>,
    private val purchaseAmount: PurchaseAmount,
) {
    fun calculateEarningRate(): EarningRate {
        val winningAmount = calculateWinningAmount()
        val rate = winningAmount.toDouble() / purchaseAmount.amount
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

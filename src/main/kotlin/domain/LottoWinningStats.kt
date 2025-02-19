package domain

import Rank
import domain.value.EarningRate
import domain.value.PurchaseAmount

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

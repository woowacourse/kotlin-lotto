package lotto.domain.model

import lotto.domain.value.PurchaseAmount
import lotto.enums.Rank

class LottoResult(
    val winningStats: Map<Rank, Int>,
) {
    fun getEarningRate(purchaseAmount: PurchaseAmount): Double {
        val winningAmount = getTotalWinningAmount()
        val rate = winningAmount.toDouble() / purchaseAmount.amount
        return rate
    }

    private fun getTotalWinningAmount(): Int = winningStats.entries.sumOf { (rank, count) -> rank.winningMoney * count }
}

package model.winning

import model.Money
import model.Quantity
import model.profit.ProfitRate

data class WinningResult(val result: Map<WinningRank, Quantity>) {
    fun calculateProfitRate(purchaseAmount: Money): ProfitRate {
        val totalProfit = result.entries.sumOf { it.key.winningPrize * it.value }
        return ProfitRate(totalProfit / purchaseAmount)
    }
}

private inline fun <T> Iterable<T>.sumOf(selector: (T) -> Money): Money {
    var sum: Money = Money.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

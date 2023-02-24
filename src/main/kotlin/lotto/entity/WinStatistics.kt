package lotto.entity

import lotto.model.Rank

class WinStatistics(val value: Map<Rank, Int>) {
    fun calculateWinMoney(): Money =
        Money(value.map { it.key.winningMoney.value * it.value }.sum())

    fun calculateProfitRate(purchaseMoney: PurchaseMoney): ProfitRate =
        ProfitRate(calculateWinMoney().value.toFloat() / purchaseMoney.value)
}

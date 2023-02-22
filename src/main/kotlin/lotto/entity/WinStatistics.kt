package lotto.entity

import lotto.model.Rank

class WinStatistics(val value: List<Rank>) {
    fun calculateWinMoney(): Money = Money(value.sumOf { it.winningMoney.value })

    fun calculateProfitRate(purchaseMoney: PurchaseMoney): ProfitRate =
        ProfitRate(calculateWinMoney().value.toFloat() / purchaseMoney.value)
}

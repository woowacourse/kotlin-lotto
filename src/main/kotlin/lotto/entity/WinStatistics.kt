package lotto.entity

import lotto.model.Rank

class WinStatistics(val value: List<Rank>) {
    fun calculateWinMoney(): Money = Money(value.sumOf { it.winningMoney.value })
}

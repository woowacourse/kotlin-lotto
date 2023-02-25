package lotto.entity

import lotto.model.Rank

class WinStatistics(val value: List<Rank>) {
    fun sumWiningMoney(): Long = value.sumOf { it.winningMoney }
}

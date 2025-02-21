package lotto.model

import lotto.contants.Constants

class LottoResult(
    private val ranks: List<Rank>,
) {
    fun getWinningStatus(): Map<Rank, Int> = ranks.groupingBy { it }.eachCount()

    fun calculateProfit(): Double {
        val totalWinningMoney = ranks.sumOf { it.winningMoney }
        return totalWinningMoney / (ranks.size * Constants.LOTTO_AMOUNT).toDouble()
    }
}

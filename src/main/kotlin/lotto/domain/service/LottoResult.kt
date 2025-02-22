package lotto.domain.service

import lotto.Constants
import lotto.domain.model.Rank

class LottoResult(
    private val ranks: List<Rank>,
) {
    fun getWinningStatus(): Map<Rank, Int> = ranks.groupingBy { it }.eachCount()

    fun calculateProfit(): Double {
        val totalWinningMoney = ranks.sumOf { it.winningMoney }
        return totalWinningMoney / (ranks.size * Constants.LOTTO_AMOUNT).toDouble()
    }
}

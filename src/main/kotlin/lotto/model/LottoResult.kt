package lotto.model

import lotto.Rank

class LottoResult(
    private val ranks: List<Rank>,
) {
    fun calculateProfit(): Double {
        val totalWinningMoney = ranks.sumOf { it.winningMoney }
        return totalWinningMoney / (ranks.size * 1_000).toDouble()
    }
}

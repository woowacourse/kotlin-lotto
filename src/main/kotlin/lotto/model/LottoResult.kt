package lotto.model

import lotto.contants.LottoRuleConstants

class LottoResult(
    private val ranks: List<Rank>,
) {
    fun getWinningStatus(): Map<Rank, Int> = ranks.groupingBy { it }.eachCount()

    fun calculateProfit(): Double {
        val totalWinningMoney = ranks.sumOf { it.winningMoney }
        return totalWinningMoney / (ranks.size * LottoRuleConstants.LOTTO_AMOUNT).toDouble()
    }
}

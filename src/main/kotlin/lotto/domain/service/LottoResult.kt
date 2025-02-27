package lotto.domain.service

import lotto.Constants
import lotto.domain.model.Rank

class LottoResult(
    val ranks: Map<Rank, Int>,
) {
    constructor(ranks: List<Rank>) : this(ranks.groupingBy { it }.eachCount())

    fun calculateProfit(): Double {
        val totalWinningMoney =
            ranks.entries.sumOf { (key, value) ->
                key.winningMoney * value
            }
        return totalWinningMoney / (ranks.values.sum() * Constants.LOTTO_AMOUNT).toDouble()
    }
}

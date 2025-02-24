package lotto.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

class LottoResult(val lottoRanks: Map<LottoRank, Int>) {
    constructor(ranks: List<LottoRank>) : this(ranks.groupingBy { it }.eachCount())

    fun calculateTotalReturn(lottoPrice: Int): BigDecimal {
        val totalWinningAmount =
            lottoRanks.entries.sumOf { (rank, count) ->
                rank.winningAmount.toBigDecimal() * count.toBigDecimal()
            }
        val totalSpent = (lottoRanks.values.sum() * lottoPrice).toBigDecimal()
        return totalWinningAmount.divide(totalSpent, SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

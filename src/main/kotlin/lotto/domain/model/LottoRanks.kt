package lotto.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

class LottoRanks(val lottoRanks: Map<LottoRank, Int>) {
    fun calculateTotalReturn(lottoPrice: Int = Lotto.LOTTO_PRICE): BigDecimal {
        val rankWinningAmounts =
            lottoRanks.map { (key, value) -> key.winningAmount.toBigDecimal() * value.toBigDecimal() }
        val totalWinningAmount = rankWinningAmounts.sumOf { it }
        val totalSpent = (lottoRanks.values.sumOf { it } * lottoPrice).toBigDecimal()
        return totalWinningAmount.divide(totalSpent, SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

package lotto.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

class LottoResult(val lottoRanks: List<LottoRank>) {
    fun calculateTotalReturn(lottoPrice: Int): BigDecimal {
        val totalWinningAmount = lottoRanks.sumOf { it.winningAmount.toBigDecimal() }
        val totalSpent = (lottoRanks.size * lottoPrice).toBigDecimal()

        return totalWinningAmount.divide(totalSpent, SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

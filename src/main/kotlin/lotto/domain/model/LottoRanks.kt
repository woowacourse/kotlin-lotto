package lotto.domain.model

import lotto.domain.LottoRules
import java.math.BigDecimal
import java.math.RoundingMode

class LottoRanks(val lottoRanks: List<LottoRank>) {
    fun calculateTotalReturn(): BigDecimal {
        val totalWinningAmount = lottoRanks.sumOf { it.winningAmount.toBigDecimal() }
        val totalSpent = (lottoRanks.size * LottoRules.LOTTO_PRICE.value).toBigDecimal()

        return totalWinningAmount.divide(totalSpent, SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

package lotto.domain.service

import lotto.LOTTO_PRICE
import lotto.domain.model.Rank
import java.math.BigDecimal
import java.math.RoundingMode

class TotalReturnCalculator {
    fun calculate(ranks: List<Rank>): BigDecimal {
        val totalReturn = BigDecimal(ranks.sumOf { it.winningAmount } / (ranks.size * LOTTO_PRICE).toDouble())
        return totalReturn.setScale(SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

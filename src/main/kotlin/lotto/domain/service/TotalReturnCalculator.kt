package lotto.domain.service

import lotto.domain.LottoRules
import lotto.domain.model.LottoRank
import java.math.BigDecimal
import java.math.RoundingMode

class TotalReturnCalculator {
    fun calculate(lottoRanks: List<LottoRank>): BigDecimal {
        val totalReturn = BigDecimal(lottoRanks.sumOf { it.winningAmount } / (lottoRanks.size * LottoRules.LOTTO_PRICE.value).toDouble())
        return totalReturn.setScale(SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

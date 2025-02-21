package lotto.domain.model

import lotto.domain.LottoRules
import java.math.BigDecimal
import java.math.RoundingMode

class LottoRanks(val lottoRanks: List<LottoRank>) {
    fun calculateTotalReturn(): BigDecimal {
        val totalReturn = BigDecimal(lottoRanks.sumOf { it.winningAmount } / (lottoRanks.size * LottoRules.LOTTO_PRICE.value).toDouble())
        return totalReturn.setScale(SCALE_BOUND, RoundingMode.DOWN)
    }

    private companion object {
        const val SCALE_BOUND = 2
    }
}

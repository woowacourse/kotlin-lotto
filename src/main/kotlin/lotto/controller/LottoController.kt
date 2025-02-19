package lotto.controller

import lotto.domain.LottoResult

object LottoController {
    fun getProfitRate(
        lottoResults: List<LottoResult>,
        price: Int,
    ): Double {
        val profit: Long = lottoResults.sumOf { lottoResult: LottoResult -> lottoResult.prizeAmount.toLong() }
        val profitRate = profit / price.toDouble()
        return profitRate
    }
}

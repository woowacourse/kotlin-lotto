package lotto.domain

import kotlin.math.floor

class LottoResults(
    val value: List<LottoResult>
) {
    fun getProfitRate(): Double {
        val profit: Long = value.sumOf { lottoResult: LottoResult ->
            lottoResult.prizeAmount.toLong()
        }
        return floor(profit / (value.size * Lotto.PRICE).toDouble() * 100) / 100
    }
}

package lotto.domain

class LottoResults(
    val value: List<LottoResult>
) {
    fun getProfitRate(): Double {
        val profit: Long = value.sumOf { lottoResult: LottoResult ->
            lottoResult.prizeAmount.toLong()
        }
        return profit / (value.size * Lotto.PRICE).toDouble()
    }
}

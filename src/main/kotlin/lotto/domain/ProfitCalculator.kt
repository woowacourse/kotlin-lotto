package lotto.domain

class ProfitCalculator {
    fun calculateProfitRate(results: List<LottoResult>): Double {
        val profit: Long = results.sumOf { lottoResult: LottoResult -> lottoResult.prizeAmount.toLong() }
        val profitRate = profit / (results.size * Lotto.PRICE).toDouble()
        return profitRate
    }
}

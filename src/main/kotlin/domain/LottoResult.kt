package domain

data class LottoResult(private val result: Map<Rank, Int>) {
    val rateOfReturn: Double
        get() = revenue / investment

    private val investment: Double
        get() = result.values.sum().toDouble() * LottoStore.LOTTO_PRICE

    private val revenue: Double
        get() = result.map { it.key.winningMoney.toDouble() * it.value }.sum()

    init {
        require(investment > 0) { ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE }
    }

    operator fun get(rank: Rank): Int = result[rank] ?: 0

    companion object {
        private const val ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE = "적어도 하나 이상의 복권을 넣어야합니다."
    }
}

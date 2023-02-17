package domain

data class LottoResult(private val result: Map<Rank, Int>) {
    init {
        require(getInvestment() > 0) { ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE }
    }
    fun get(): Map<Rank, Int> = result

    operator fun get(rank: Rank): Int = result[rank] ?: 0

    fun sum(): Int = result.values.sum()

    fun getRateOfReturn(): Double = getRevenue() / getInvestment()

    private fun getInvestment(): Double = result.values.sum().toDouble() * LottoStore.LOTTO_PRICE
    private fun getRevenue(): Double = result.map { it.key.winningMoney.toDouble() * it.value }.sum()

    companion object {
        private const val ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE = "적어도 하나 이상의 복권을 넣어야합니다."
    }
}

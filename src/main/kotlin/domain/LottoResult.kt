package domain

data class LottoResult(private val result: Map<Rank, Int>) : Map<Rank, Int> by result {
    init {
        require(result.values.sum() > 0) { ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE }
    }
    fun getRateOfReturn(): Double = getRevenue() / getInvestment()

    private fun getInvestment(): Double = result.values.sum().toDouble() * LottoStore.LOTTO_PRICE
    private fun getRevenue(): Double = result.map { it.key.winningMoney.toDouble() * it.value }.sum()

    companion object {
        private const val ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE = "적어도 하나 이상의 복권을 넣어야합니다."
        fun of(lottos: List<Lotto>, winningLotto: WinningLotto): LottoResult =
            LottoResult(
                Rank.values().associateWith { rank ->
                    lottos.count { rank == Rank.valueOf(winningLotto.getCountOfMatch(it), winningLotto.matchBonus(it)) }
                },
            )
    }
}

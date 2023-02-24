package domain

data class LottoResult(private val result: Map<Rank, Int>) {

    fun getRateOfReturn(): Double {
        if (result.values.sum() == 0) return 0.0
        return getRevenue() / getInvestment()
    }

    private fun getInvestment(): Double = result.values.sum().toDouble() * LottoStore.LOTTO_PRICE
    private fun getRevenue(): Double = result.map { it.key.winningMoney.toDouble() * it.value }.sum()
    operator fun get(rank: Rank) = result[rank] ?: 0

    companion object {
        fun of(lottos: List<Lotto>, winningLotto: WinningLotto): LottoResult =
            LottoResult(lottos.asSequence().map { winningLotto.getRankOf(it) }.groupingBy { it }.eachCount())
    }
}

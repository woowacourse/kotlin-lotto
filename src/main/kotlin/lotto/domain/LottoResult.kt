package lotto.domain

class LottoResult(
    private val lottos: List<Lotto>,
    private val winningLotto: WinningLotto,
) {
    fun getRanks(): List<Rank> {
        return lottos.map { lotto -> winningLotto.getRank(lotto) }
    }

    fun getWinningStatistics(): Map<Rank, Int> {
        return getRanks().groupingBy { it }.eachCount()
    }

    fun calculateProfitRate(price: Int): Double {
        val totalWinningMoney = getRanks().sumOf { it.winningMoney }
        val totalCost = Purchase(price).getPrice()
        return totalWinningMoney.toDouble() / totalCost
    }
}

package lotto.domain

class LottoResult(
    private val lottos: List<Lotto>,
    private val winningLotto: WinningLotto,
) {
    fun getRanks(): List<Rank> {
        return lottos.map { lotto ->
            lotto.getRank(winningLotto)
        }
    }

    fun getWinningStatistics(): Map<Rank, Int> {
        return getRanks().groupingBy { it }.eachCount()
    }

    fun calculateProfitRate(): Double {
        val totalWinningMoney = getRanks().sumOf { it.winningMoney }
        val totalCost = lottos.size * Purchase.LOTTO_PRICE
        return totalWinningMoney.toDouble() / totalCost
    }
}

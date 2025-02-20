package lotto.domain

class LottoResult(
    private val lottos: List<Lotto>,
    private val winningLotto: WinningLotto,
) {
    fun getRanks(): List<Rank> {
        return lottos.map { lotto ->
            val matchCount = lotto.compareWithWinningLotto(winningLotto.winningLotto)
            val matchBonus = lotto.compareWithBonusNumber(winningLotto.bounusNumber.toInt())
            Rank.valueOf(matchCount, matchBonus)
        }
    }

    fun getWinningStatistics(): Map<Rank, Int> {
        return getRanks().groupingBy { it }.eachCount()
    }

    fun calculateProfitRate(): Double {
        val totalWinningMoney = getRanks().sumOf { it.winningMoney }
        val totalCost = lottos.size * 1000
        return totalWinningMoney.toDouble() / totalCost
    }
}

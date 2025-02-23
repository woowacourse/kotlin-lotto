package lotto.model

class LottoMatchCalculator {
    fun getWinningCounts(
        lottoBundle: List<Lotto>,
        winningLotto: WinningLotto,
    ): Map<Rank, Int> {
        val result = Rank.entries.associateWith { 0 }.toMutableMap()
        lottoBundle.forEach { lotto ->
            val rank = determineRank(lotto, winningLotto)
            result[rank] = result[rank]!! + 1
        }
        return result
    }

    private fun determineRank(
        lotto: Lotto,
        winningLotto: WinningLotto,
    ): Rank {
        return Rank.valueOf(winningLotto.getCountOfMatch(lotto), winningLotto.isBonusNumberMatch(lotto))
    }
}

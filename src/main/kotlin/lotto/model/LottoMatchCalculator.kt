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
        val matchResult = winningLotto.match(lotto)
        return Rank.valueOf(matchResult.matchCount, matchResult.isBonusMatch)
    }
}

package lotto.model

class WinningStatistics(
    lottoBundle: List<Lotto>,
    winningLotto: WinningLotto,
) {
    private val statistics: Map<Rank, Int> = calculateStatistics(lottoBundle, winningLotto)

    private fun calculateStatistics(
        lottoBundle: List<Lotto>,
        winningLotto: WinningLotto,
    ): Map<Rank, Int> {
        val result = Rank.entries.associateWith { 0 }.toMutableMap()
        lottoBundle.forEach { lotto ->
            val rank = Rank.fromMatch(winningLotto.getCountOfMatch(lotto), winningLotto.isBonusNumberMatch(lotto))
            result[rank] = result.getOrDefault(rank, 0) + 1
        }
        return result
    }

    fun getAllStatistics(): Map<Rank, Int> = statistics
}

package lotto.model

class WinningStatistics(
    private val statistics: Map<Rank, Int>,
) {
    companion object {
        fun calculateStatistics(
            lottoBundle: List<Lotto>,
            winningLotto: WinningLotto,
        ): WinningStatistics {
            val result = Rank.entries.associateWith { 0 }.toMutableMap()
            lottoBundle.forEach { lotto ->
                val rank = Rank.fromMatch(winningLotto.getCountOfMatch(lotto), winningLotto.isBonusNumberMatch(lotto))
                result[rank] = result.getOrDefault(rank, 0) + 1
            }
            return WinningStatistics(result)
        }
    }

    fun getAllStatistics(): Map<Rank, Int> = statistics
}

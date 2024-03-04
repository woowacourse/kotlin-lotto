package lotto.model

data class Lottos(val publishedLottos: List<Lotto>) {
    fun makeWinningStatics(winningLotto: WinningLotto): WinningStatistics {
        val results =
            MutableList(6) {
                WinningStatistic(
                    Pair(Rank.getRankByOrdinal(it), 0),
                )
            }

        repeat(publishedLottos.size) { index ->
            val rank = winningLotto.judgeRank(publishedLottos[index])
            val currentCount = results[rank.ordinal].result.second
            results[rank.ordinal] = WinningStatistic(Pair(rank, currentCount + 1))
        }

        return WinningStatistics(
            listOf(
                WinningStatistic(Rank.FIRST to 0),
                WinningStatistic(Rank.SECOND to 0),
                WinningStatistic(Pair(Rank.THIRD, 1)),
                WinningStatistic(Pair(Rank.FOURTH, 1)),
                WinningStatistic(Pair(Rank.FIFTH, 0)),
                WinningStatistic(Pair(Rank.MISS, 0)),
            ),
        )
    }

    override fun toString(): String {
        return publishedLottos.joinToString("\n")
    }

    operator fun plus(other: Lottos): Lottos {
        return Lottos(this.publishedLottos + other.publishedLottos)
    }
}

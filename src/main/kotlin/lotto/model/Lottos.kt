package lotto.model

import lotto.util.LottoConstants

data class Lottos(val publishedLottos: List<Lotto>) {
    fun makeWinningStatics(winningLotto: WinningLotto): WinningStatistics {
        val results =
            MutableList(LottoConstants.SIZE) {
                WinningStatistic(Pair(Rank.getRankByOrdinal(it), 0))
            }

        repeat(publishedLottos.size) { index ->
            val rank = winningLotto.judgeRank(publishedLottos[index])
            val currentCount = results[rank.ordinal].result.second
            results[rank.ordinal] = WinningStatistic(Pair(rank, currentCount + 1))
        }

        return WinningStatistics(results)
    }

    override fun toString(): String {
        return publishedLottos.joinToString("\n")
    }

    operator fun plus(other: Lottos): Lottos {
        return Lottos(this.publishedLottos + other.publishedLottos)
    }
}

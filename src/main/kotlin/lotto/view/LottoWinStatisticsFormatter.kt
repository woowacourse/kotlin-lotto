package lotto.view

import lotto.entity.WinStatistics
import lotto.model.Rank

class LottoWinStatisticsFormatter : WinStatisticsFormatter {
    override fun format(winStatistics: WinStatistics): String {
        val ranks = Rank.values()
        val reversedRanks = ranks.reversed()
        val targetRanks = reversedRanks.filter { it != Rank.MISS }
        return targetRanks.joinToString("\n") {
            formatRank(it, winStatistics)
        }
    }

    private fun formatRank(rank: Rank, winStatistics: WinStatistics): String {
        var additionalMessage = " "

        if (rank == Rank.SECOND)
            additionalMessage = ", 보너스 볼 일치"

        val countOfRank = winStatistics.value.getOrDefault(rank, 0)

        return "${rank.countOfMatch}개 일치$additionalMessage(${rank.winningMoney.value}원) - ${countOfRank}개"
    }
}

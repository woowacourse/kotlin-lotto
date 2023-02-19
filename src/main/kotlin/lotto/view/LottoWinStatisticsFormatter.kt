package lotto.view

import lotto.entity.WinStatistics
import lotto.model.Rank

class LottoWinStatisticsFormatter : WinStatisticsFormatter {
    override fun format(winStatistics: WinStatistics): String {
        val ranks = Rank.values()
        val targetRanks = ranks.reversed().filter { it != Rank.MISS }
        return targetRanks.joinToString("\n") {
            formatRank(it, winStatistics)
        }
    }

    private fun formatRank(rank: Rank, winStatistics: WinStatistics): String {
        var additionalMessage = " "

        if (rank == Rank.SECOND)
            additionalMessage = ", 보너스 볼 일치"

        return ("${rank.countOfMatch}개 일치$additionalMessage(${rank.winningMoney.value}원) - ${winStatistics.value.count { it.name == rank.name }}개")
    }
}

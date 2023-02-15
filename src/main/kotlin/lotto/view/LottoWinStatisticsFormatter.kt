package lotto.view

import lotto.entity.WinStatistics
import lotto.model.Rank

class LottoWinStatisticsFormatter : WinStatisticsFormatter {
    override fun format(winStatistics: WinStatistics): String {
        return Rank.values().reversed().map {
            formatRank(it, winStatistics)
        }.joinToString { "\n" }
    }

    private fun formatRank(rank: Rank, winStatistics: WinStatistics): String {
        var additionalMessage = " "

        if (rank == Rank.MISS)
            return ""
        if (rank == Rank.SECOND)
            additionalMessage = ", 보너스 볼 일치"

        return ("${rank.countOfMatch}개 일치$additionalMessage(${rank.winningMoney}원) - ${winStatistics.value.count { it.name == rank.name }}개")
    }
}

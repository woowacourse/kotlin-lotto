package lotto.view

import lotto.entity.WinStatistics
import lotto.model.Rank

interface WinStatisticsFormatter {
    fun format(rank: Rank, winStatistics: WinStatistics): String
}

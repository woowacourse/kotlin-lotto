package lotto.view

import lotto.entity.WinStatistics

interface WinStatisticsFormatter {
    fun format(winStatistics: WinStatistics): String
}

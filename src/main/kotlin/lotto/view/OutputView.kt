package lotto.view

import lotto.entity.WinStatistics
import lotto.model.Rank

class OutputView {
    fun winStatisticsResult(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        println("당첨 통계")
        println("---------")
        Rank.values().reversed().forEach { println(winStatisticsFormatter.format(it, winStatistics)) }
    }

    companion object {
        private const val MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요."
        private const val MESSAGE_PURCHASE_COUNT = "개를 구매했습니다."
        private const val MESSAGE_WIN_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_BONUS = "보너스 볼을 입력해 주세요."
    }
}

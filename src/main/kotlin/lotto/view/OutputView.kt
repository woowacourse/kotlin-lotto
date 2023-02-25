package lotto.view

import lotto.entity.Lottos
import lotto.entity.ProfitRate
import lotto.entity.WinStatistics
import kotlin.math.floor

class OutputView {

    fun printMessage(message: String, vararg args: Any?) {
        println(String.format(message, *args))
    }

    fun winStatisticsResult(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        println("당첨 통계")
        println("---------")
        println(winStatisticsFormatter.format(winStatistics))
    }

    fun profitRateResult(profitRate: ProfitRate) {
        println("총 수익률은 ${floor((profitRate.value * 100)) / 100}입니다.")
    }

    fun lottosResult(lottos: Lottos) {
        lottos.value.forEach {
            println(it)
        }
    }

    companion object {
        const val MESSAGE_PURCHASE_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
    }
}

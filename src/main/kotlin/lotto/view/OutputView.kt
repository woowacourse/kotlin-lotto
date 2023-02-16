package lotto.view

import lotto.entity.Lotto
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
            println(formatLotto(it))
        }
    }

    private fun formatLotto(lotto: Lotto): String {
        return "[${lotto.numbers.joinToString(", ") { it.value.toString() }}]"
    }

    companion object {
        const val MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요."
        const val MESSAGE_PURCHASE_COUNT = "%d개를 구매했습니다."
        const val MESSAGE_WIN_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_BONUS = "보너스 볼을 입력해 주세요."
    }
}

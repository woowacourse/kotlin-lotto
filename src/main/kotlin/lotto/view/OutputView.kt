package lotto.view

import lotto.entity.Lotto
import lotto.entity.ProfitRate
import lotto.entity.PurchasedLottos
import lotto.entity.WinStatistics

class OutputView {
    fun printMessage(message: String, vararg args: Any?) {
        println(String.format(message, *args))
    }

    fun winStatisticsResult(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        println(MESSAGE_WIN_STATISTICS)
        println(MESSAGE_HORIZONTAL_LINE)
        println(winStatisticsFormatter.format(winStatistics))
    }

    fun profitRateResult(profitRate: ProfitRate) {
        val p = profitRate.roundDown()
        printMessage(MESSAGE_PROFIT_RATE, p)
    }

    fun gameResult(purchasedLottos: PurchasedLottos) {
        purchasedLottos.value.forEach {
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
        const val MESSAGE_WIN_STATISTICS = "당첨 통계"
        const val MESSAGE_PROFIT_RATE = "총 수익률은 %.2f입니다."
        const val MESSAGE_HORIZONTAL_LINE = "----------"
    }
}

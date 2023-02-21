package lotto.view

import lotto.domain.PurchaseAmount
import lotto.domain.WinningResult

class OutputView {

    fun printMessage(message: String) {
        println(message)
    }

    fun printWinningStats(amount: PurchaseAmount, result: WinningResult) {
        println()
        println(NOTICE_MESSAGE_WINNING_STAT)
        println(DIVIDER_LINE)

        result.getResult().reversed().forEach { println(it) }

        print(NOTICE_FORMAT_MESSAGE_TOTAL_YIELD.format(result.calculateYield(amount.amount)))
        if (!result.isGain(result.calculateYield(amount.amount))) println(NOTICE_MESSAGE_NOT_GAIN)
    }

    companion object {
        private const val DIVIDER_LINE = "---------"

        private const val NOTICE_FORMAT_MESSAGE_TOTAL_YIELD = "총 수익률은 %.2f입니다."
        private const val NOTICE_MESSAGE_NOT_GAIN = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        private const val NOTICE_MESSAGE_WINNING_STAT = "당첨 통계"
    }
}

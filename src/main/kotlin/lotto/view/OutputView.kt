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
    }

    companion object {
        private const val DIVIDER_LINE = "---------"

        private const val NOTICE_MESSAGE_WINNING_STAT = "당첨 통계"

        private const val NOTICE_FORMAT_MESSAGE_TOTAL_YIELD = "총 수익률은 %.2f입니다."
    }
}

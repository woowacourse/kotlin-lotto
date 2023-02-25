package lotto.view

import lotto.domain.Lotteries
import lotto.domain.PurchaseAmount
import lotto.domain.Rank
import lotto.domain.WinningResult

class OutputView {

    fun printPurchaseLotteries(amount: PurchaseAmount, lotteries: Lotteries) {
        println()
        println(NOTICE_FORMAT_MESSAGE_PURCHASE_QUANTITY.format(amount.autoNumber, amount.autoNumber))
        repeat(lotteries.size) { index ->
            println(lotteries.get(index))
        }
    }

    fun printWinningStats(winningResult: WinningResult) {
        println()
        println(NOTICE_MESSAGE_WINNING_STAT)
        println(DIVIDER_LINE)

        printMatches(winningResult)

        print(NOTICE_FORMAT_MESSAGE_TOTAL_YIELD.format(winningResult.yield))
        if (!winningResult.isGain) println(NOTICE_MESSAGE_NOT_GAIN)
    }

    private fun printMatches(winningResult: WinningResult) {
        val countMatchRanks = winningResult.toMap()
        val texts: MutableList<String> = mutableListOf()

        Rank.values().associateWith {
            if (it != Rank.MISS)
                texts.add("${it.description} (${it.winningMoney})- ${countMatchRanks[it]}")
        }

        texts.reversed().forEach { println(it) }
    }

    companion object {
        private const val DIVIDER_LINE = "---------"

        private const val NOTICE_FORMAT_MESSAGE_PURCHASE_QUANTITY = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val NOTICE_FORMAT_MESSAGE_TOTAL_YIELD = "총 수익률은 %.2f입니다."
        private const val NOTICE_MESSAGE_NOT_GAIN = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        private const val NOTICE_MESSAGE_WINNING_STAT = "당첨 통계"
    }
}

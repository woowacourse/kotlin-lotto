package lotto.view

import lotto.model.LottoTicket
import lotto.model.Rank
import java.math.RoundingMode
import java.text.DecimalFormat

class OutputView {
    fun printPurchaseCount(count: Int) {
        println(MESSAGE_PURCHASE.format(count))
    }

    fun printLotto(lottoTicket: LottoTicket) {
        println(lottoTicket.getNumbers().map { it.number }.joinToString(SEPARATOR, PREFIX, POSTFIX))
    }

    fun printResult(results: Map<Rank, Int>) {
        println(MESSAGE_RESULT_HEADER)
        for (result in results) {
            val matchMessage =
                when (result.key) {
                    Rank.FIRST -> MESSAGE_MATCH_SIX
                    Rank.SECOND -> MESSAGE_MATCH_FIVE_BONUS
                    Rank.THIRD -> MESSAGE_MATCH_FIVE
                    Rank.FOURTH -> MESSAGE_MATCH_FOUR
                    Rank.FIFTH -> MESSAGE_MATCH_THREE
                    Rank.MISS -> MESSAGE_MATCH_MISS
                }
            val prizeMessage =
                when (result.key) {
                    Rank.FIRST -> Rank.FIRST.winningMoney
                    Rank.SECOND -> Rank.SECOND.winningMoney
                    Rank.THIRD -> Rank.THIRD.winningMoney
                    Rank.FOURTH -> Rank.FOURTH.winningMoney
                    Rank.FIFTH -> Rank.FIFTH.winningMoney
                    Rank.MISS -> Rank.MISS.winningMoney
                }

            println(MESSAGE_RESULT.format(matchMessage, prizeMessage, result.value))
        }
    }

    fun printProfit(profit: Double) {
        val df = DecimalFormat(PATTERN_DECIMAL_POINT)
        df.roundingMode = RoundingMode.DOWN
        val formattedProfit = df.format(profit)
        println(MESSAGE_PROFIT.format(formattedProfit))
    }

    companion object {
        private const val MESSAGE_PURCHASE = "%d개를 구매했습니다."
        private const val MESSAGE_RESULT_HEADER = "\n" + "당첨 통계" + "\n" + "---------"
        private const val MESSAGE_MATCH_SIX = "6개 일치"
        private const val MESSAGE_MATCH_FIVE_BONUS = "5개 일치, 보너스 볼 일치"
        private const val MESSAGE_MATCH_FIVE = "5개 일치"
        private const val MESSAGE_MATCH_FOUR = "4개 일치"
        private const val MESSAGE_MATCH_THREE = "3개 일치"
        private const val MESSAGE_MATCH_MISS = "0개 일치"
        private const val MESSAGE_RESULT = "%s (%d원) - %d개"
        private const val MESSAGE_PROFIT = "총 수익률은 %s입니다."

        private const val SEPARATOR = ", "
        private const val PREFIX = "["
        private const val POSTFIX = "]"
        private const val PATTERN_DECIMAL_POINT = "#.##"
    }
}

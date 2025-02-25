package lotto.view

import lotto.domain.model.LottoTicket
import lotto.domain.model.ProfitStatus
import lotto.domain.model.Rank
import java.math.RoundingMode
import java.text.DecimalFormat

class OutputView {
    fun printPurchaseCount(count: Int) {
        println(MESSAGE_PURCHASE.format(count))
    }

    fun printLotto(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach { ticket ->
            println(ticket.getNumbers().map { it.number }.joinToString(SEPARATOR, PREFIX, POSTFIX))
        }
    }

    private fun getMatchMessage(rank: Rank): String =
        when (rank) {
            Rank.FIRST -> MESSAGE_MATCH_SIX
            Rank.SECOND -> MESSAGE_MATCH_FIVE_BONUS
            Rank.THIRD -> MESSAGE_MATCH_FIVE
            Rank.FOURTH -> MESSAGE_MATCH_FOUR
            Rank.FIFTH -> MESSAGE_MATCH_THREE
            Rank.MISS -> MESSAGE_MATCH_MISS
        }

    fun printResult(results: Map<Rank, Int>) {
        println(MESSAGE_RESULT_HEADER)
        results.forEach { (rank, count) ->
            val matchMessage = getMatchMessage(rank)
            println(MESSAGE_RESULT.format(matchMessage, rank.winningMoney, count))
        }
    }

    fun printProfit(profit: Double) {
        val df = DecimalFormat(PATTERN_DECIMAL_POINT)
        df.roundingMode = RoundingMode.DOWN
        val formattedProfit = df.format(profit)
        print(MESSAGE_PROFIT.format(formattedProfit))
        println(getProfitStatus(profit))
    }

    private fun getProfitStatus(profit: Double) =
        when (ProfitStatus.getMessage(profit)) {
            ProfitStatus.GAIN -> MESSAGE_PROFIT_GAIN
            ProfitStatus.BREAKEVEN -> MESSAGE_PROFIT_BREAKEVEN
            ProfitStatus.LOSS -> MESSAGE_PROFIT_LOSS
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
        private const val MESSAGE_PROFIT_GAIN = " (기준이 1이기 때문에 결과적으로 이득이라는 의미임)"
        private const val MESSAGE_PROFIT_BREAKEVEN = " (기준이 1이기 때문에 결과적으로 본전이라는 의미임)"
        private const val MESSAGE_PROFIT_LOSS = " (기준이 1이기 때문에 결과적으로 손해라는 의미임)"

        private const val SEPARATOR = ", "
        private const val PREFIX = "["
        private const val POSTFIX = "]"
        private const val PATTERN_DECIMAL_POINT = "#.##"
    }
}

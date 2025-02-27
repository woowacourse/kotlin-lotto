package lotto.view

import lotto.domain.model.LottoTicket
import lotto.domain.model.ProfitStatus
import lotto.domain.model.Rank
import java.math.RoundingMode
import java.text.DecimalFormat

class OutputView {
    fun printPurchaseCount(
        manualCount: Int,
        autoCount: Int,
    ) {
        println(MESSAGE_PURCHASE.format(manualCount, autoCount))
    }

    fun printManualNumbersGuide() = println(MESSAGE_OUTPUT_MANUAL_NUMBERS)

    fun printLotto(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach { ticket ->
            println(ticket.toSet().map { it.number })
        }
    }

    fun printResult(results: Map<Rank, Int>) {
        println(MESSAGE_RESULT_HEADER)
        Rank.entries.sortedBy { it.winningMoney }.filterNot { it == Rank.MISS }.forEach { rank ->
            val matchBonus = if (rank == Rank.SECOND) MESSAGE_MATCH_BONUS else ""
            println(
                MESSAGE_MATCH_COUNT.format(
                    rank.countOfMatch,
                    matchBonus,
                    rank.winningMoney,
                    results.getOrDefault(rank, 0),
                ),
            )
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
        private const val MESSAGE_OUTPUT_MANUAL_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_PURCHASE = "\n수동으로 %d장, 자동으로 %d장을 구매했습니다."
        private const val MESSAGE_RESULT_HEADER = "\n" + "당첨 통계" + "\n" + "---------"
        private const val MESSAGE_MATCH_BONUS = "보너스 볼 일치"
        private const val MESSAGE_PROFIT = "총 수익률은 %s입니다."
        private const val MESSAGE_PROFIT_GAIN = " (기준이 1이기 때문에 결과적으로 이득이라는 의미임)"
        private const val MESSAGE_PROFIT_BREAKEVEN = " (기준이 1이기 때문에 결과적으로 본전이라는 의미임)"
        private const val MESSAGE_PROFIT_LOSS = " (기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        private const val MESSAGE_MATCH_COUNT = "%d개 일치%s (%d원) - %d개"

        private const val PATTERN_DECIMAL_POINT = "#.##"
    }
}

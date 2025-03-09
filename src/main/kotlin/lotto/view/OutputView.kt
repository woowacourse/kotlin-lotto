package lotto.view

import lotto.domain.Lotto
import lotto.util.Rank
import kotlin.math.floor

class OutputView {
    fun printPurchasedLottoTickets(
        manualLottoCount: Int,
        lottoTickets: List<Lotto>,
    ) {
        println(MESSAGE_BUY_LOTTO.format(manualLottoCount, lottoTickets.size - manualLottoCount))
        lottoTickets.forEach { lotto ->
            println(lotto.lotto.joinToString(", ", "[", "]") { it.number.toString() })
        }
    }

    fun printWinningStats(winningStats: Map<Rank, Int>) {
        println(MESSAGE_WINNING_STATS)
        for ((state, count) in winningStats.toList().reversed()) {
            printWinningStat(state, count)
        }
    }

    fun printProfit(profit: Double) {
        val formattedProfit = floor(profit * 100) / 100
        println(MESSAGE_PROFIT.format(formattedProfit))
    }

    fun printInvalidLottoCountMessage(maxCount: Int) {
        println(ERROR_NOT_INVALID_MANUAL_LOTTO_COUNT.format(maxCount))
    }

    private fun printWinningStat(
        state: Rank,
        count: Int,
    ) {
        if (state == Rank.SECOND) {
            printWinningStatWithBonusBall(state, count)
        } else {
            printWinningStatWIthNoBonusBall(state, count)
        }
    }

    private fun printWinningStatWIthNoBonusBall(
        state: Rank,
        count: Int,
    ) {
        println(MESSAGE_MATCH_COUNT.format(state.countOfMatch, state.price, count))
    }

    private fun printWinningStatWithBonusBall(
        state: Rank,
        count: Int,
    ) {
        println(MESSAGE_MATCH_COUNT_WITH_BONUS_BALL.format(state.countOfMatch, state.price, count))
    }

    fun printErrorMessage(exception: Throwable) {
        println(exception)
    }

    companion object {
        const val MESSAGE_BUY_LOTTO = "\n수동으로 %d개, 자동으로 %d개를 구매했습니다."
        const val MESSAGE_WINNING_STATS = "\n당첨 통계\n---------"
        const val MESSAGE_MATCH_COUNT = "%d개 일치 (%d원)- %d개"
        const val MESSAGE_MATCH_COUNT_WITH_BONUS_BALL = "%d개 일치, 보너스 볼 일치(%d원)- %d개"
        const val MESSAGE_PROFIT = "총 수익률은 %.2f입니다."
        const val ERROR_NOT_INVALID_MANUAL_LOTTO_COUNT = "[ERROR] 구입 가능한 수동 로또는 %d개 이하입니다. 다시 입력해주세요"
    }
}

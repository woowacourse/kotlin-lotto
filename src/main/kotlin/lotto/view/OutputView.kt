package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.util.Rank

class OutputView {
    fun printPurchasedLottos(lottos: List<Lotto>) {
        println(MESSAGE_BUY_LOTTO.format(lottos.size))
        lottos.forEach { lotto ->
            printLottoNumber(lotto.numbers)
        }
    }

    fun printLottoNumber(numbers: List<LottoNumber>) {
        println(numbers.map { it.number })
    }

    fun printWinningStats(winningStats: Map<Rank, Int>) {
        println(MESSAGE_WINNING_STATS)
        for ((state, count) in winningStats) {
            printWinningStat(state, count)
        }
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

    fun printProfit(profit: Double) {
        println(MESSAGE_PROFIT.format(profit))
    }

    companion object {
        const val MESSAGE_BUY_LOTTO = "%d개를 구매했습니다."
        const val MESSAGE_WINNING_STATS = "\n당첨 통계\n---------"
        const val MESSAGE_MATCH_COUNT = "%d개 일치 (%d원)- %d개"
        const val MESSAGE_MATCH_COUNT_WITH_BONUS_BALL = "%d개 일치, 보너스 볼 일치(%d원)- %d개"
        const val MESSAGE_PROFIT = "총 수익률은 %.2f입니다."
    }
}
